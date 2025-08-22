CREATE TABLE PAGAMENTO (
    id SERIAL PRIMARY KEY,
    data_hora TIMESTAMP,
    tipo VARCHAR(2)
);

ALTER TABLE pedido
ADD COLUMN id_pagamento INTEGER NOT NULL;

ALTER TABLE pedido
ADD CONSTRAINT fk_pagamento
FOREIGN KEY (id_pagamento) REFERENCES pagamento(id);
