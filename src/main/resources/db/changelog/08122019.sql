INSERT INTO department (id, name, note)
VALUES (111, 'Отдел по делам всех отделов', 'Всем отделам отдел');
INSERT INTO department (id, name, note, parent_department_id)
VALUES (112, 'Отдел по делам спорта и физической культуры', 'Что то там делают', 111);
INSERT INTO department (id, name, note, parent_department_id)
VALUES (113, 'Отдел индустрии туризма', 'И эти что то делают', 111);

INSERT INTO profession (id, name, note)
VALUES (1, 'Team Lead', 'Всё расскажет, всё подскажет' );
INSERT INTO profession (id, name, note)
VALUES (2, 'Assistant', 'Швец, жнец и на дуде игрец' );
INSERT INTO profession (id, name, note)
VALUES (3, 'DevOps', 'Гик' );

INSERT INTO employee (first_name, second_name, patronymic, note, profession_id, department_id)
VALUES ('Томас', 'Мидлдич', null, 'Tomcat started on port(s): 8080', 1, 111);
INSERT INTO employee (first_name, second_name, patronymic, note, profession_id, department_id)
VALUES ('Мартин', 'Старр', null, 'Initializing ExecutorService applicationTaskExecutor', 3, 112);
INSERT INTO employee (first_name, second_name, patronymic, note, profession_id, department_id)
VALUES ('Зак', 'Вудс', null, 'LiveReload server is running on port 35729', 2, 113);
