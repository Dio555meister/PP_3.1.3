package com.UsersMVC.users.models;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles") // имя таблицы
public class Role implements GrantedAuthority {

    @Id // идентиф. сущности
    @GeneratedValue(strategy = GenerationType.IDENTITY) //  стратегия генерации значения идентификатора
    private int id;

    @Column(name = "name") // Указание имени столбца в таблице
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() { // наш конструктор по умолчанию
    }

    public Role(String name) { // Конструктор с параметром name
        this.name = name;
    }

    @Override
    public String getAuthority() { // Реализация метода getAuthority() интерфейса GrantedAuthority.
        return name;
    }
}
