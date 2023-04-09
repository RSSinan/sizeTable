//
// Created by Turgay Hopal on 17.03.2023.
//

#include "main.h"

char cardId[CARD_SIZE];
uint8_t rdmBuffer[RDM_READ_SIZE];
float dist1AdcList[DIST_READINGS] = {0};
float dist2AdcList[DIST_READINGS] = {0};

static int com_bytes_rdm = 0;
static int com_tail_rdm = 0;

static uint16_t dist1ReadIndex = 0;
static uint16_t dist2ReadIndex = 0;

static float dist1VoltageTotal = 0.0f;
static float dist2VoltageTotal = 0.0f;

void on_uart_rx() {
    while (uart_is_readable(RDM_UART)) {
        uint8_t ch = uart_getc(RDM_UART);
        rdmBuffer[com_tail_rdm] = ch;
        com_tail_rdm = (com_tail_rdm == (RDM_READ_SIZE - 1)) ? 0 : (com_tail_rdm + 1);
        com_bytes_rdm++;
        if (com_bytes_rdm == RDM_READ_SIZE) {
            com_bytes_rdm = 0;
            com_tail_rdm = 0;

            if (rdmBuffer[0] == RDM_PCK_HEAD) {
                if (rdmBuffer[RDM_READ_SIZE - 1] == RDM_PCK_END) {
                    memset(cardId, RDM_DEF_PATTERN, CARD_SIZE);
                    for (uint8_t i = 0; i < CARD_SIZE; i++) {
                        cardId[i] = (char) rdmBuffer[i + 3];
                    }
                }
            }

        }
    }
}

int main() {
    stdio_init_all();
    setupGpio();
    setupAdc();
    setupRdmUart();

    memset(cardId, RDM_DEF_PATTERN, CARD_SIZE);

    while (1) {
        int c = getchar_timeout_us(2000);
        if (c != -1) {
            if ((char) c == LASER_1_ON) {
                setLaser(LASER_1_PIN, 1);
            } else if ((char) c == LASER_2_ON) {
                setLaser(LASER_2_PIN, 1);
            } else if ((char) c == LASER_3_ON) {
                setLaser(LASER_3_PIN, 1);
            } else if ((char) c == LASER_4_ON) {
                setLaser(LASER_4_PIN, 1);
            } else if ((char) c == LASER_1_OFF) {
                setLaser(LASER_1_PIN, 0);
            } else if ((char) c == LASER_2_OFF) {
                setLaser(LASER_2_PIN, 0);
            } else if ((char) c == LASER_3_OFF) {
                setLaser(LASER_3_PIN, 0);
            } else if ((char) c == LASER_4_OFF) {
                setLaser(LASER_4_PIN, 0);
            } else if ((char) c == GET_RFID_CARD) {
                uint8_t checksum = calculateChecksumCardId();
                checksum += (char) '-';
                checksum &= 0x7F;
                printf("%s-%d\r\n", cardId, checksum);
            } else if ((char) c == CLEAR_RFID_CARD) {
                memset(cardId, RDM_DEF_PATTERN, CARD_SIZE);
            } else if ((char) c == GET_DISTANCES) {
                float dist_1_val = calculateVoltageToMills(getDistanceSens1VoltageValue());
                float dist_2_val = calculateVoltageToMills(getDistanceSens2VoltageValue());
                printf("%02.1f-%02.1f-\r\n", dist_1_val, dist_2_val);
            }
        }
    }
}

void setupGpio(void) {
    gpio_init(LASER_1_PIN);
    gpio_init(LASER_2_PIN);
    gpio_init(LASER_3_PIN);
    gpio_init(LASER_4_PIN);

    gpio_set_dir(LASER_1_PIN, GPIO_OUT);
    gpio_set_dir(LASER_2_PIN, GPIO_OUT);
    gpio_set_dir(LASER_3_PIN, GPIO_OUT);
    gpio_set_dir(LASER_4_PIN, GPIO_OUT);
}

void setupAdc(void) {
    adc_init();

    adc_gpio_init(SENS_1_AIN_PIN);
    adc_gpio_init(SENS_2_AIN_PIN);
}

uint16_t getDist1AnalogValue(void) {
    adc_select_input(0);
    uint16_t val = adc_read();
    return val;
}

uint16_t getDist2AnalogValue(void) {
    adc_select_input(1);
    uint16_t val = adc_read();
    return val;
}

float calculateAnalogToVoltage(uint16_t analog_value) {
    const float conversion_factor = 3.3f / (1 << 12);
    return (float)(analog_value * conversion_factor);
}

float calculateVoltageToMills(float voltage) {
    return (float) (voltage * (41.0 / 3.3));
}

float getDistanceSens1VoltageValue(void) {
    dist1VoltageTotal -= dist1AdcList[dist1ReadIndex];
    dist1AdcList[dist1ReadIndex] = calculateAnalogToVoltage(getDist1AnalogValue());
    dist1VoltageTotal += dist1AdcList[dist1ReadIndex];
    dist1ReadIndex += 1;

    if (dist1ReadIndex >= DIST_READINGS) {
        dist1ReadIndex = 0;
    }

    return (float) (dist1VoltageTotal / DIST_READINGS);
}

float getDistanceSens2VoltageValue(void) {
    dist2VoltageTotal -= dist2AdcList[dist2ReadIndex];
    dist2AdcList[dist2ReadIndex] = calculateAnalogToVoltage(getDist2AnalogValue());
    dist2VoltageTotal += dist2AdcList[dist2ReadIndex];
    dist2ReadIndex += 1;

    if (dist2ReadIndex >= DIST_READINGS) {
        dist2ReadIndex = 0;
    }

    return (float) (dist2VoltageTotal / DIST_READINGS);
}

uint8_t calculateChecksumCardId(void) {
    uint8_t checksum = 0;
    for (int i = 0; i < CARD_SIZE; ++i) {
        checksum += (uint8_t) cardId[i];
    }

    checksum &= 0x7F;

    return checksum;

}

void setLaser(uint gpio, uint val) {
    gpio_put(gpio, val);
}

void setupRdmUart(void) {
    uart_init(RDM_UART, 9600);

    gpio_set_function(RDM_RX_PIN, GPIO_FUNC_UART);
    gpio_set_function(RDM_TX_PIN, GPIO_FUNC_UART);

    uart_set_fifo_enabled(RDM_UART, true);

    irq_set_exclusive_handler(UART0_IRQ, on_uart_rx);
    irq_set_enabled(UART0_IRQ, true);

    uart_set_irq_enables(RDM_UART, true, false);

}

clock_t getTick(void) {
    return (clock_t) (time_us_64() / 10000);
}