--
-- JBoss, Home of Professional Open Source
-- Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
-- contributors by the @authors tag. See the copyright.txt in the
-- distribution for a full listing of individual contributors.
--
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- http://www.apache.org/licenses/LICENSE-2.0
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

-- You can use this file to load seed data into the database using SQL statements
insert into Member (id, name, email, phone_number) values (0, 'John Smith', 'john.smith@mailinator.com', '2125551212') 


INSERT INTO ACCURACYBYCLASS(ID, FMEASURE, FPRATE, MCC, PRCAREA, PRECISION, PREDICATIONCLASS, RECALL, ROCAREA, TPRATE, VERSION, MODELO_ID) VALUES 
(1, 0.651, 0.071, 0.422, 0.958, 0.933, 'SIM', 0.5, 0.917, 0.5, 1, 1), 
(2, 0.634, 0.5, 0.422, 0.788, 0.481, 'NAO', 0.929, 0.917, 0.929, 1, 1), 
(3, 0.651, 0.071, 0.422, 0.958, 0.933, 'SIM', 0.5, 0.917, 0.5, 1, 2), 
(4, 0.634, 0.5, 0.422, 0.788, 0.481, 'NAO', 0.929, 0.917, 0.929, 1, 2), 
(5, 0.651, 0.071, 0.422, 0.958, 0.933, 'SIM', 0.5, 0.917, 0.5, 1, 3), 
(6, 0.634, 0.5, 0.422, 0.788, 0.481, 'NAO', 0.929, 0.917, 0.929, 1, 3);
INSERT INTO CLASSIFICACAO(ID, ATTRIBUTESLIST, ATTRIBUTESTOTAL, DATE, INSTANCESTOTAL, RELATION, SCHEME, TESTMODE, VERSION, MODELO_ID) VALUES 
(1, '20', 42, DATE '2017-01-22', 42, 'POO_variaveis_classificacao_v1.3.0_weka_dados_pessoais', 'weka.classifiers.bayes.NaiveBayes', '10', 0, 1), 
(2, 'abcd', 10, DATE '2017-01-22', 50, 'POO_variaveis_classificacao_v1.3.0_weka_dados_pessoais', 'weka.classifiers.bayes.Net', '10-fold', 0, 2), 
(3, 'abcd', 10, DATE '2017-01-22', 20, 'POO_variaveis_classificacao_v1.3.0_weka_dados_pessoais', 'weka.classifiers.bayes.Net', '10-fold', 0, 3);
INSERT INTO CLASSIFICACAOINSTANCIA(ID, DATE, DISTRIBUTIONPROBABILITY, DROPOUTPREDICTION, DROPOUTTRUTH, PREDICTIONCORRECT, USERID, VERSION, MODELO_ID) VALUES 
(1, TIMESTAMP '2017-01-22 22:08:00.0', '[0.9999999972720576, 2.7279425121080067E-9]', 'NAO', 'NAO', TRUE, 'john@acme.co', 1, 1), 
(2, TIMESTAMP '2017-01-22 22:08:00.0', '[0.9999999972720576, 2.7279425121080067E-9]', 'NAO', 'NAO', TRUE, 'john@acme.co', 1, 2), 
(3, TIMESTAMP '2017-01-22 22:08:00.0', '[0.9999999972720576, 2.7279425121080067E-9]', 'NAO', 'NAO', TRUE, 'john@acme.co', 1, 3), 
(4, TIMESTAMP '2017-01-22 22:08:00.0', '[0.9999999972720576, 2.7279425121080067E-9]', 'SIM', 'SIM', TRUE, 'mary@acme.co', 1, 3);
INSERT INTO MODELOCLASSIFICACAO(ID, ALGORITHM, CORRECTLYCLASSIFIED, DATE, INCORRECTLYCLASSIFIED, KAPPASTATISTIC, MEANABSOLUTEERROR, RELATIVEABSOLUTEERROR, ROOTMEANSQUAREDERROR, ROOTRELATIVESQUAREDERROR, VERSION) VALUES 
(1, 'Naive Bayes', 27.0, TIMESTAMP '2017-01-22 22:08:00.0', 15.0, 0.3478, 0.3365, 74.9149, 0.5661, 119.4694, 0), 
(2, 'Naive Bayes', 27.0, TIMESTAMP '2017-01-22 22:08:00.0', 15.0, 0.3478, 0.3365, 74.9149, 0.5661, 119.4694, 0), 
(3, 'Naive Bayes', 27.0, TIMESTAMP '2017-01-22 22:08:00.0', 15.0, 0.3478, 0.3365, 74.9149, 0.5661, 119.4694, 0);





--INSERT INTO ANALISEDESEMPENHO VALUES (6,'BKT', 'teste.csv', NOW(), 50, 0);