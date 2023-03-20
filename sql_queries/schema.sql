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

CREATE TABLE IF NOT EXISTS weather_info
(
    id BIGSERIAL PRIMARY KEY,
    country VARCHAR(100),
    locality VARCHAR(200),
    date_time TIMESTAMP,
    temperature_c DECIMAL(3, 1),
    wind_speed_m_in_s DECIMAL(3, 1),
    atmospheric_pressure_hPa DECIMAL(7, 3),
    air_humidity INTEGER,
    weather_condition VARCHAR(100)
    );

INSERT INTO weather_info (country, locality, date_time, temperature_c, wind_speed_m_in_s, atmospheric_pressure_hPa, air_humidity, weather_condition)
VALUES ('Belarus', 'Minsk', '1997-12-17 07:37:16-08', 15.0, 1.2, 1195.1, 60, 'aaaa');