CREATE TABLE IF NOT EXISTS vetclinic.duty_schedules
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    day_of_week bigint NOT NULL,
    start_time time without time zone NOT NULL,
    end_time time without time zone NOT NULL,
    CONSTRAINT duty_schedules_pkey PRIMARY KEY (id)
    )