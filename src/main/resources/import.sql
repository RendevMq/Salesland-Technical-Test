-- ===============================
-- TABLA: AUX_PROVEEDORES
-- ===============================
INSERT INTO aux_proveedores (cod_proveedor, proveedor) VALUES ('INC', 'Increnta');
INSERT INTO aux_proveedores (cod_proveedor, proveedor) VALUES ('OTRO', 'Proveedor Secundario');

-- ===============================
-- TABLA: AUX_CAMPANAS
-- ===============================
INSERT INTO aux_campanas (servidor, bbdd_report, id_campana, sistema, Nombre, activo, spcarga_ws_salesland_leads, admite_duplicado) VALUES ('srv1', 'report1', 23, 'ICR', 'Campaña Test', 1, 'spCarga', 1);
INSERT INTO aux_campanas (servidor, bbdd_report, id_campana, sistema, Nombre, activo, spcarga_ws_salesland_leads, admite_duplicado) VALUES ('srv2', 'report2', 15, 'A8', 'Campaña Altitude', 1, 'spCargaAlt', 0);

-- ===============================
-- TABLA: AUX_DISOCIAR (campos que SIEMPRE se encriptan)
-- ===============================
INSERT INTO aux_disociar (campo) VALUES ('nombre');
INSERT INTO aux_disociar (campo) VALUES ('ape1');
INSERT INTO aux_disociar (campo) VALUES ('direccion');
INSERT INTO aux_disociar (campo) VALUES ('email');
INSERT INTO aux_disociar (campo) VALUES ('nif');
INSERT INTO aux_disociar (campo) VALUES ('telefono');

-- ===============================
-- TABLA: AUX_CAMPANA_DISOCIAR (campos extra por campaña)
-- ===============================
-- Por ejemplo, para la campaña 23
INSERT INTO aux_campana_disociar (campana, campo, tipo) VALUES (23, 'comentario', 'descripcion');
INSERT INTO aux_campana_disociar (campana, campo, tipo) VALUES (23, 'referencia', 'codigo');
-- Para la campaña 15
INSERT INTO aux_campana_disociar (campana, campo, tipo) VALUES (15, 'documento', 'archivo');



