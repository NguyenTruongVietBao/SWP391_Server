package com.math.mathcha.entity;

public class Example {

    /** user role=parent payment course for student, 1 course many chapter -> many topic -> many lesson
     *   =   1-1
     *      Cart
     *          @OneToOne(mappedBy="cart")
     *          private Item item
     *      Item
     *          @OneToOne(mappedBy="item")
     *          private Cart cart
     *
     *   =   1-n
     *      Cart
     *          @OneToMany(mappedBy="cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
     *          private List<Item> item
     *      Item
     *          @ManyToOne
     *          @JoinColumn(name = "cart_id", nullable = false)
     *          private Cart cart;
     *
     *   =   n-n
     *      Cart
     *          @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
     *          @JoinColumn(name = "cart_item",
     *                      joinColumn = @JoinColumn(name = "cart_id"),
     *                      inverseJoinColumn = @JoinColumn(name = "item_id"))
     *          private List<Item> item
     *      Item
     *          @ManyToMany(mappedBy ="item")
     *          private List<User> user;
     *
     */
}
