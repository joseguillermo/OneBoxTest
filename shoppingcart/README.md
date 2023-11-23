# Technical test for OneBox

## Shopping cart endpoints

* Create a shopping cart: POST /shoppingcart

as in: curl -X POST localhost:8080/shoppingcart -H 'Content-type:application/json' -d {}

* Read all shopping carts: GET /shoppingcart

as in: curl -v localhost:8080/shoppingcart

* Read a shopping cart: GET /shoppingcart/{id}

as in: curl -v localhost:8080/shoppingcart/1

* Update (add one or more products to a shopping cart) a shopping cart: PUT /shoppingcart/{id}

as in:  curl -X PUT localhost:8080/shoppingcart/1 -H 'Content-type:application/json' -d '[1,2,3]'

Products with id from 1 to 6 are preloaded.

* Delete a shopping cart: DELETE /shoppingcart/{id}

as in: curl -X DELETE localhost:8080/shoppingcart/1
