CREATE TABLE IF NOT EXISTS vetclinic.pets
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    name varchar(200)  NOT NULL,
    type_id bigint NOT NULL,
    birth_date date NOT NULL,
    CONSTRAINT pets_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pets_types FOREIGN KEY (type_id)
    REFERENCES vetclinic.types (id)
    )