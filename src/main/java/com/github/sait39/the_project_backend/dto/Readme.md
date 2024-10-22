# Guide to DTOs

Why I use it:

Rest APIs should not deliver the whole model to the user, so we limit the data, necessary to be sent or received by the user,
by just accepting or sending out the data as a DTO.

We don't want a user to receive their password through a request, but it is in the model.
So when it is in the model and we say we return a User. Technically the request has t
return a password. But with a DTO we can just not send it over.

---

When should you use them?

Source: https://www.baeldung.com/java-dto-pattern

DTOs come in handy in systems with remote calls, as they help to reduce the number of them.

DTOs also help when the domain model is composed of many different objects and the presentation model needs all their data at once, or they can even reduce roundtrip between client and server.

With DTOs, we can build different views from our domain models, allowing us to create other representations of the same domain but optimizing them to the clients’ needs without affecting our domain design. Such flexibility is a powerful tool to solve complex problems.

---