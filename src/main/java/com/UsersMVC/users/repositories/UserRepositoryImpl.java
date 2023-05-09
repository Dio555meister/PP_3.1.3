package com.UsersMVC.users.repositories;


import com.UsersMVC.users.models.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository

// Определяем UserRepositoryImpl, который реализует интерфейс UserRepository
public class UserRepositoryImpl implements UserRepository {


    // Позволяет выполнять операции с бд
    @PersistenceContext
    private EntityManager entityManager;


    // index возвращает список всех пользователей из бд.Создается запрос "from User",выполняется с помощью entityManager-> возвращает список результатов.
    @Override
    public List<User> index() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    // show, возвращает пользователя с указанным идентификатором из базы данных
    // Использует find entityManager для поиска пользователя по его идентификатору
    @Override
    public User show(int id) {
        return entityManager.find(User.class, id);
    }

    // save сохраняет нового пользователя в бд
    // persist entityManager для сохранения пользователя.
    public void save(User person) {
        entityManager.persist(person);
    }

    // update обновляет данные в бд. Находит пользователя с помощью метода find entityManager->обновляет его->
    // и выполняет слияние с помощью метода merge entityManager.
    @Override
    public void update(int id, User user) {
        User user1 = entityManager.find(User.class, id);
        if (user1 != null) {
            user1.setEmail(user.getEmail());
            user1.setAge(user.getAge());
            user1.setName(user.getName());
            user1.setPassword(user.getPassword());
            entityManager.merge(user);

        }
    }


    // delete удаляет пользователя из бд.Находит пользователя  методм find entityManager-> удаляет его методом remove entityManager
    @Override
    public void delete(int id) {
        User person = entityManager.find(User.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }

    // findByUsername ищет пользователя по имени в бд. Делается запрос createQuery entityManager
    // Устанавливается параметр "name" с помощью метода setParameter-> и выполняет запрос получая результаты в виде списка.
    // Если список пуст, возвращается null, иначе возвращается первый элемент списка
    @Override
    public User findByUsername(String name) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.name = :name",
                User.class);
        query.setParameter("name", name);
        List<User> results = query.getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
