INSERT INTO pap_data (id,  name, pap_group_type) VALUES (1,   'TESD Policies and Plans', 'TESDPP');

INSERT INTO success_indicator_data (id,  target, measures, pap_data_id, is_accumulated, is_percentage) VALUES (1,   94, 'stakeholders who rate policies/ plans as good or better', 1, 'N', 'Y');

INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'LEYTE_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'BILIRAN_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'SOUTHERN_LEYTE_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'SAMAR_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'EASTERN_SAMAR_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'NORTHERN_SAMAR_PO', 1);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  94, 100, 106.00, 'TESDA_RO', 1);


INSERT INTO pap_data (id,  name, pap_group_type) VALUES (2,   'TESD Committees', 'TESDPP');


INSERT INTO success_indicator_data (id,  target, measures, pap_data_id, is_accumulated, is_percentage) VALUES (2,   7, 'TESDCs maintained and strengthened observing the new normal protocols', 2, 'y', 'N');

INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'LEYTE_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'BILIRAN_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'SOUTHERN_LEYTE_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'SAMAR_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'EASTERN_SAMAR_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'NORTHERN_SAMAR_PO', 2);
INSERT INTO operating_unit_data (id,  target, output, rate, operating_unit_type, success_indicator_data_id) VALUES (nextval('hibernate_sequence'),  1, 1, 100.00, 'TESDA_RO', 2);