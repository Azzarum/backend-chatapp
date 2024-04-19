package net.devazzarum.chatapp.Models;

import jakarta.persistence.*;


@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "contact_user_id")
    private User contactUser;


    public Contact(User user, User contactUser) {
        this.user = user;
        this.contactUser = contactUser;
    }


    public Contact() {

    }
}
