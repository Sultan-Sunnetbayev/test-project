CREATE TABLE users(
    `id` INT AUTO_INCREMENT NOT NULL ,
    `name` VARCHAR (200) NOT NULL ,
    `email` VARCHAR (100) NOT NULL UNIQUE ,

    CONSTRAINT `users_id_pk`
        PRIMARY KEY(`id`)
);

CREATE TABLE gifts(

    `id` INT AUTO_INCREMENT NOT NULL ,
    `name` VARCHAR (200) NOT NULL ,
    `user_id` INT ,
    `gift_id` INT ,

    CONSTRAINT `gifts_id_pk`
        PRIMARY KEY (`id`) ,
    CONSTRAINT `gifts_user_id_fk`
        FOREIGN KEY(`user_id`)
            REFERENCES `users`(`id`)
                  ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT `gifts_gift_id_fk`
        FOREIGN KEY(`gift_id`)
            REFERENCES `gifts`(`id`)
                ON UPDATE CASCADE ON DELETE CASCADE
);