package com.haulmont.testtask;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.sql.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Long.lowestOneBit;
import static java.lang.Long.valueOf;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    private static SessionFactory buildSessionFactory(){
        try{
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml").build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        }catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed"+ ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private void client_function() {
        GridLayout l_client = new GridLayout(); //FormLayout
        l_client.setWidthUndefined(); // Ползунок
//        l_client_1.setSpacing(false); // Compact layout
        l_client.setWidth("100%");//setSizeFull();
        l_client.setMargin(true);
        setContent(l_client);
        Button b_client_add = new Button("Добавить клиента");
        l_client.addComponent(b_client_add);
        Button b_client_edit = new Button("Изменить данные клиента");
        l_client.addComponent(b_client_edit);
        Button b_client_delete = new Button("Удалить клиента");
        l_client.addComponent(b_client_delete);

        Button b_client_exit = new Button("Выход");
        l_client.addComponent(b_client_exit);
        Button b_client_update = new Button("Обновить данные");
        l_client.addComponent(b_client_update);


        SessionFactory sf = buildSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin();
        Client cl = new Client();

        List<Client> list = session.createQuery("FROM Client", Client.class).list();
        list.forEach(p-> l_client.addComponent(
                new Label(String.valueOf
                        ("Id: \"" +p.getClient_id()+ "\","+"  ФИО: \""+p.getName_client()
                        + "\","+"  Телефон: \""+p.getPhone()+ "\","+"  Эл. почта: \""+p.getEmail()+ "\","+"  Номер паспорта: \""+p.getPassport() +"\"."))));

        //-------------
        TextField name = new TextField("Введите ФИО клиента");
        TextField phone = new TextField("Введите телефон клиента");
        TextField email = new TextField("Введите эл. почту клиента");
        TextField passport = new TextField("Введите номер паспорта клиента");
        name.setRequired(true);
        phone.setRequired(true);
        email.setRequired(true);
        passport.setRequired(true);
        Button b_client_add_enter = new Button("Добавить клиента");

        TextField number_edit = new TextField("Введите Id клиента которого вы хотите изименить");
        number_edit.setRequired(true);
        Button b_client_edit_enter = new Button("Ввод");

        Button b_client_edit_enter_changes = new Button("Внести изменения");

        TextField number_delete = new TextField("Введите Id клиента, которого вы хотите удалить");
        number_delete.setRequired(true);
        Button b_client_delete_enter = new Button("Удалить");


        b_client_add.addClickListener((Button.ClickListener)
        clickEvent->{
            l_client.addComponent(name);
            l_client.addComponent(phone);
            l_client.addComponent(email);
            l_client.addComponent(passport);

            l_client.addComponent(b_client_add_enter);

            b_client_add_enter.addClickListener((Button.ClickListener)
            ClickEvent->{

                cl.setName_client(name.getValue());
                cl.setPhone(phone.getValue());
                cl.setEmail(email.getValue());
                cl.setPassport(passport.getValue());
                session.saveOrUpdate(cl);
                session.getTransaction().commit(); //  Подтверждает rolback - откатывает
            });

        });

        b_client_edit.addClickListener((Button.ClickListener)
                clickEvent->{

                    l_client.addComponent(number_edit);
                    l_client.addComponent(b_client_edit_enter);

            b_client_edit_enter.addClickListener((Button.ClickListener)
            ClickEvent->{


                List<Client> list_edit = session.createQuery("from Client where client_id = "+ number_edit.getValue(), Client.class).list();
                list_edit.forEach(p-> {
                    name.setValue(p.getName_client());
                    l_client.addComponent(name);
                    phone.setValue(p.getPhone());
                    l_client.addComponent(phone);
                    email.setValue(p.getEmail());
                    l_client.addComponent(email);
                    passport.setValue(p.getPassport());
                    l_client.addComponent(passport);

                    l_client.addComponent(b_client_edit_enter_changes);

                    b_client_edit_enter_changes.addClickListener((Button.ClickListener)
                            ClickEvent_for_changes->{

                                p.setName_client(name.getValue());
                                p.setPhone(phone.getValue());
                                p.setPhone(email.getValue());
                                p.setPassport(passport.getValue());
                                session.saveOrUpdate(p);
                                session.getTransaction().commit(); //  Подтверждает rolback - откатывает
                    });

                });

            });

        });

        b_client_delete.addClickListener((Button.ClickListener)
        clickEvent->{
            l_client.addComponent(number_delete);

            l_client.addComponent(b_client_delete_enter);

            b_client_delete_enter.addClickListener((Button.ClickListener)
            ClickEvent->{

                Query q = session.createQuery("delete Client where client_id = "+ number_delete.getValue());
                q.executeUpdate();
            });

        });

        b_client_update.addClickListener((Button.ClickListener)
                clickEvent->{
                    session.close();
                    client_function();
                });
        b_client_exit.addClickListener((Button.ClickListener)
                ClickEvent->{
                    setup();
                    session.close();
                });
    }


    private void credit_function() {
        GridLayout l_credit = new GridLayout(); //FormLayout
        l_credit.setWidthUndefined(); // Ползунок
//        l_client_1.setSpacing(false); // Compact layout
        l_credit.setWidth("100%");//setSizeFull();
        l_credit.setMargin(true);
        setContent(l_credit);

        Button b_credit_add = new Button("Добавить кредит");
        l_credit.addComponent(b_credit_add);
        Button b_credit_edit = new Button("Изменить кредит");
        l_credit.addComponent(b_credit_edit);
        Button b_credit_delete = new Button("Удалить кредит");
        l_credit.addComponent(b_credit_delete);

        Button b_credit_exit = new Button("Выход");
        l_credit.addComponent(b_credit_exit);
        Button b_credit_update = new Button("Обновить данные");
        l_credit.addComponent(b_credit_update);

        SessionFactory sf = buildSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin();
        Credit cr = new Credit();

        List<Credit> list = session.createQuery("FROM Credit", Credit.class).list();
        list.forEach(p -> l_credit.addComponent(
                new Label(String.valueOf
                        ("Id: \"" + p.getCredit_id() + "\",  Лимит по кридиту:  \"" + p.getLimit_credit()+"\"," + " Процентная ставка:  \"" + p.getRate()+"\"."))));

//---------------
        TextField limit = new TextField("Введите лимит по кредиту");
        TextField rate = new TextField("Введите процентную ставку");
        Button b_credit_add_enter = new Button("Добавить кредит");

        TextField number_edit = new TextField("Введите Id кредита который хотите изменить");
        Button b_credit_edit_enter = new Button("Ввод");

        Button b_credit_edit_enter_changes = new Button("Применить изменения");

        TextField number_delete = new TextField("Введите номер кредита, который хотите удалить");
        Button b_credit_delete_enter = new Button("Удалить");

        b_credit_add.addClickListener((Button.ClickListener)
                clickEvent -> {
                    l_credit.addComponent(limit);
                    l_credit.addComponent(rate);
                    l_credit.addComponent(b_credit_add_enter);

                    b_credit_add_enter.addClickListener((Button.ClickListener)
                            clickEvent1 -> {
                                cr.setLimit_credit(valueOf(limit.getValue()));
                                cr.setRate(valueOf(rate.getValue()));
                                session.saveOrUpdate(cr);
                                session.getTransaction().commit(); //  Подтверждает rolback - откатывает
                            });
                });

        b_credit_edit.addClickListener((Button.ClickListener)
                clickEvent -> {
                    l_credit.addComponent(number_edit);
                    l_credit.addComponent(b_credit_edit_enter);
                    b_credit_edit_enter.addClickListener((Button.ClickListener)
                            ClickEvent -> {
                                List<Credit> list_edit = session.createQuery("from Credit where credit_id = "
                                        + number_edit.getValue(), Credit.class).list();
                                list_edit.forEach(p -> {
                                    limit.setValue(String.valueOf(p.getLimit_credit()));
                                    l_credit.addComponent(limit);
                                    rate.setValue(String.valueOf(p.getRate()));
                                    l_credit.addComponent(rate);

                                    l_credit.addComponent(b_credit_edit_enter_changes);
                                    b_credit_edit_enter_changes.addClickListener((Button.ClickListener)
                                            ClickEvent_for_changes -> {
                                        p.setLimit_credit(valueOf(limit.getValue()));
                                        p.setRate(valueOf(rate.getValue()));
                                        session.saveOrUpdate(p);
                                        session.getTransaction().commit(); //  Подтверждает rolback - откатывает

                                            });

                                });
                            });
                });

        b_credit_delete.addClickListener((Button.ClickListener)
                clickEvent->{
            l_credit.addComponent(number_delete);
            l_credit.addComponent(b_credit_delete_enter);
            b_credit_delete_enter.addClickListener((Button.ClickListener)
                    ClickEvent->{
                Query q = session.createQuery("delete Credit where credit_id = "+ number_delete.getValue());
                q.executeUpdate();

                    });
                });

        b_credit_update.addClickListener((Button.ClickListener)
                clickEvent->{
                    session.close();
                    credit_function();
                });
        b_credit_exit.addClickListener((Button.ClickListener)
                ClickEvent->{
                    setup();
                    session.close();
                });

    }

    private void offer_function() {
        GridLayout l_offer = new GridLayout(); //FormLayout
        l_offer.setWidthUndefined(); // Ползунок
//        l_client_1.setSpacing(false); // Compact layout
        l_offer.setWidth("100%");//setSizeFull();
        l_offer.setMargin(true);
        setContent(l_offer);

        Button b_offer_add = new Button("Добавить предложение");
        l_offer.addComponent(b_offer_add);
        Button b_offer_edit = new Button("Изменить предложение");
        l_offer.addComponent(b_offer_edit);
        Button b_offer_delete = new Button("Удалить предложение");
        l_offer.addComponent(b_offer_delete);

        Button b_offer_exit = new Button("Выход");
        l_offer.addComponent(b_offer_exit);
        Button b_offer_update = new Button("Обновить данные");
        l_offer.addComponent(b_offer_update);


        SessionFactory sf = buildSessionFactory();
        Session session = sf.openSession();
        session.getTransaction().begin();
        Offer of = new Offer();

        List<Offer> list = session.createQuery("FROM Offer", Offer.class).list();
        list.forEach(p -> l_offer.addComponent(
                new Label(String.valueOf
                        ("Id: " + p.getOffer_id() + "Оформлен на клиента с Id: \"" + p.getOffer_client_id() + "\", Вид кредита Id:  \""
                                + p.getOffer_credit_id()+"\", Сумма кредитa:  \""+ p.getSumm() + "\", На время в месяцах: \""+ p.getMounth_num()+"\", Ежемесячный платёж:  \""
                                +p.getSumm_every_mounth()+"\", Сумма выплат:  \""+p.getSumm_itog()+"\"."))));
        //------------
        TextField client_id = new TextField("Номер клиента");
        TextField credit_id = new TextField("Вид кредита");
        TextField summ = new TextField("Выданная сумма");
        TextField mounth_num = new TextField("Количество месяцев");
        Button b_offer_add_enter = new Button("Ввод");
        TextField number_edit = new TextField("Номер предложения");
        Button b_offer_edit_enter = new Button("Ввод номера");
        Button b_offer_edit_enter_changes = new Button("Изменить");
        TextField number_delete = new TextField("Номер предложения");
        Button b_offer_delete_enter = new Button("Удалить");

        b_offer_add.addClickListener((Button.ClickListener)
                clickEvent -> {
            l_offer.addComponent(client_id);
            l_offer.addComponent(credit_id);
            l_offer.addComponent(summ);
            l_offer.addComponent(mounth_num);

            l_offer.addComponent(b_offer_add_enter);
            b_offer_add_enter.addClickListener((Button.ClickListener)
                    clickEvent1 -> {
                of.setOffer_client_id(valueOf(client_id.getValue()));
                of.setOffer_credit_id(valueOf(credit_id.getValue()));
                of.setSumm(valueOf(summ.getValue()));
                of.setMounth_num(valueOf(mounth_num.getValue()));
                //Дальше расчет по формулам

                        SessionFactory sf1 = buildSessionFactory();
                        Session session1 = sf1.openSession();
                        session1.getTransaction().begin();
                        List<Credit> list1 = session1.createQuery("from Credit where credit_id = "
                                + credit_id.getValue(), Credit.class).list();
                        list1.forEach(p -> {
                                    of.setSumm_itog((long) (Math.pow((double) (p.getRate() / 100 + 1), valueOf(mounth_num.getValue()))
                                            * valueOf(summ.getValue())));
                            of.setSumm_every_mounth((long) ((Math.pow((double) p.getRate() / 100 + 1, valueOf(mounth_num.getValue()))
                                    * valueOf(summ.getValue())) /valueOf(mounth_num.getValue())));

                        });

                session.saveOrUpdate(of);
                session.getTransaction().commit(); //  Подтверждает rolback - откатывает
                        session1.close();
                    });
            });

        b_offer_edit.addClickListener((Button.ClickListener)
                clickEvent -> {
                    l_offer.addComponent(number_edit);
                    l_offer.addComponent(b_offer_edit_enter);
                    b_offer_edit_enter.addClickListener((Button.ClickListener)
                            ClickEvent -> {
                                List<Offer> list_edit = session.createQuery("from Offer where offer_id = "
                                        + number_edit.getValue(), Offer.class).list();
                                list_edit.forEach(p -> {
                                    client_id.setValue(String.valueOf(p.getOffer_client_id()));
                                    l_offer.addComponent(client_id);
                                    credit_id.setValue(String.valueOf(p.getOffer_credit_id()));
                                    l_offer.addComponent(credit_id);
                                    summ.setValue(String.valueOf(p.getSumm()));
                                    l_offer.addComponent(summ);
                                    mounth_num.setValue(String.valueOf(p.getMounth_num()));
                                    l_offer.addComponent(mounth_num);
                                    l_offer.addComponent(b_offer_edit_enter_changes);
                                    b_offer_edit_enter_changes.addClickListener((Button.ClickListener)
                                            ClickEvent_for_changes -> {
                                                p.setOffer_client_id(valueOf(client_id.getValue()));
                                                p.setOffer_credit_id(valueOf(credit_id.getValue()));
                                                p.setSumm(valueOf(summ.getValue()));
                                                p.setMounth_num(valueOf(mounth_num.getValue()));
                                                SessionFactory sf1 = buildSessionFactory();
                                                Session session1 = sf1.openSession();
                                                session1.getTransaction().begin();
                                                List<Credit> list1 = session1.createQuery("from Credit where credit_id = "
                                                        + credit_id.getValue(), Credit.class).list();
                                                list1.forEach(pq -> {
                                                    p.setSumm_itog((long) (Math.pow((double) (pq.getRate() / 100 + 1), valueOf(mounth_num.getValue()))
                                                            * valueOf(summ.getValue())));
                                                    p.setSumm_every_mounth((long) ((Math.pow((double) pq.getRate() / 100 + 1, valueOf(mounth_num.getValue()))
                                                            * valueOf(summ.getValue())) /valueOf(mounth_num.getValue())));

                                                });

                                                session1.close();
                                                session.saveOrUpdate(p);
                                                session.getTransaction().commit(); //  Подтверждает rolback - откатывает

                                            });


                                });

                            });
                });

        b_offer_delete.addClickListener((Button.ClickListener)
                clickEvent->{
                    l_offer.addComponent(number_delete);
                    l_offer.addComponent(b_offer_delete_enter);
                    b_offer_delete_enter.addClickListener((Button.ClickListener)
                            ClickEvent->{
                                Query q = session.createQuery("delete Offer where offer_id = "+ number_delete.getValue());
                                q.executeUpdate();

                            });
                });

        b_offer_update.addClickListener((Button.ClickListener)
                clickEvent->{
                    session.close();
                    offer_function();
                });
        b_offer_exit.addClickListener((Button.ClickListener)
                ClickEvent->{
                    setup();
                    session.close();
                });

    }




    private void setup() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setMargin(true);
        setContent(layout);
        Button b_client = new Button("Клиенты");
        layout.addComponent(b_client);
        Button b_credit = new Button("Кредиты");
        layout.addComponent(b_credit);
        Button b_offer = new Button("Предложения");
        layout.addComponent(b_offer);

        b_client.addClickListener((Button.ClickListener)
                clickEvent -> {

            client_function();

                });

        b_credit.addClickListener((Button.ClickListener)
                clickEvent -> {
            credit_function();

                });

        b_offer.addClickListener((Button.ClickListener)
        ClickEvent->{
            offer_function();
        });
    }

    @Override
    protected void init(VaadinRequest request) {
        setup();

    }
}