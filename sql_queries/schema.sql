/*
 DROP TABLE weather_info;
*/


CREATE TABLE IF NOT EXISTS weather_info
(
    id BIGSERIAL PRIMARY KEY,
    country VARCHAR(100),
    locality VARCHAR(200),
    "date" DATE,
    local_time TIME,
    temperature_c DECIMAL(3, 1),
    wind_speed_m_in_s DECIMAL(3, 1),
    atmospheric_pressure_hPa DECIMAL(7, 3),
    air_humidity INTEGER,
    weather_condition VARCHAR(100)
);