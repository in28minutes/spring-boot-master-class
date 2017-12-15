## Schema


```sql
create table todo 
(id integer not null, 
desc varchar(255), 
is_done boolean not null, 
target_date timestamp, 
user varchar(255), 
primary key (id))
```

### Data

```sql
INSERT INTO TODO
VALUES(10001, 'Learn Spring Boot', false, sysdate(),'in28Minutes')

INSERT INTO TODO
VALUES(10002, 'Learn RESTful Web Services', false, sysdate(),'in28Minutes')

INSERT INTO TODO
VALUES(10003, 'Learn SOAP Web Services', false, sysdate(),'in28Minutes')
```