ALTER TABLE agendamento
    ADD COLUMN data DATE,
    ADD COLUMN hora TIME;

UPDATE agendamento
SET
  data = CAST(data_hora AS DATE),
  hora = CAST(data_hora AS TIME);

ALTER TABLE agendamento
    ALTER COLUMN data SET NOT NULL;

ALTER TABLE agendamento
    ALTER COLUMN hora SET NOT NULL;

ALTER TABLE agendamento
    DROP COLUMN data_hora;