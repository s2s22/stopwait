create table restaurant (
    id int not null auto_increment primary key,
    name varchar(30) not null,
    category_id int,
    review_id int,
    location varchar(100),
    content varchar(2000),
    rating varchar(100),
    menu varchar(1000)
)

create table review (
    id int not null auto_increment primary key,
    restaurant_id int,
    title varchar(100) not null,
    content varchar(1000) not null
)

create table restaurant_Category(
     id int not null auto_increment primary key,
    restaurantId int,
    category_id int
);

create table category(
    id int not null auto_increment primary key,
    name varchar(100) not null
);