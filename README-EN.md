## Web Store Spring boot Repository

<p>This repository contains a simple webStore in Spring Boot</p>
You're going to need a MySQL database called "web_application" to work.

- [User](#user)
- [Item](#item)
- [Category](#category)
- [Order Item](#order-item)
- [Order](#order)
- [Postman collection](./Web%20Store%20Spring.postman_collection.json)

  <h1 id="user">User</h1>
  http://localhost:8080/api/user/

  <h2>User Properties:</h2>
    <ul>
      <li>UserId</li>
      <li>Name</li>
      <li>Email</li>
      <li>WhisList (A list of items that the user wishes to purchase later)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/User/User.java">User Class</p>
  	<h2>User Methods:</h2>
    <table>
      <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Description</th>
      </tr>
      <tr>
        <td> Get All Users </td>
        <td>/getAll</td>
        <td>Returns all users</td>
      </tr>
      <tr>
        <td> Get User By Id </td>
        <td>/getById/:userId</td>
        <td>Returns a user based on their id</td>
      </tr>
      <tr>
        <td> Get User By Email </td>
        <td>/getByEmail/:email</td>
        <td>Returns a list of users with the specified email</td>
      </tr>
      <tr>
        <td> Post User </td>
        <td>/post</td>
        <td>Posts a new user</td>
      </tr>
      <tr>
        <td> Put User </td>
        <td>/put/:userId</td>
        <td>Edits a user based on their id</td>
      </tr>
      <tr>
        <td> Delete User By Id </td>
        <td>/delete/:userId</td>
        <td>Deletes a user based on their id</td>
      </tr>
      <tr>
        <td> Add Item To User Wish List </td>
        <td>/addItemToWishList/user/:userId/item/:itemId</td>
        <td>Adds an item to a user's wishlist based on their id and the item id</td>
      </tr>
      <tr>
        <td> Remove Item From User Wishlist </td>
        <td>/removeItemFromWishlist/user/:userId/item/:itemId</td>
        <td>Removes an item from a user's wishlist based on their id and the item id</td>
      </tr>
    </table>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/User/UserServiceImpl.java">User Service Implementation</p>

  <h1 id="item">Item</h1>
  http://localhost:8080/api/item

  <h2>Item Properties:</h2>
    <ul>
      <li>ItemId</li>
      <li>Name</li>
      <li>Price</li>
      <li>Stock</li>
      <li>Category (List of categories to which the item belongs)</li>
      <li>Users (List of users who added this item to their wishlist)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Itens/Item.java">Item.class</p>
  <h2>Item Methods:</h2>
    <table>
      <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Description</th>
      </tr>
      <tr>
        <td> Get All Items </td>
        <td>/getAll</td>
        <td>Returns all items</td>
      </tr>
      <tr>
        <td> Get Item By Id </td>
        <td>/getById/:itemId</td>
        <td>Returns a item based on their id</td>
      </tr>
      <tr>
        <td> Get Item By Name </td>
        <td>/getByName/:name</td>
        <td>Returns a list of items based on their name</td>
      </tr>
      <tr>
        <td> Filter Item By Price </td>
        <td>/filter/priceLimit/:priceLimit</td>
        <td>Returns a list of items based on a price limit</td>
      </tr>
      <tr>
        <td> Post Item </td>
        <td>/post</td>
        <td>Post an item</td>
      </tr>
      <tr>
        <td> Delete Item By Id </td>
        <td>/delete/:itemId</td>
        <td>Deletes a item based on their id</td>
      </tr>
      <tr>
        <td> Put Item </td>
        <td>/put/:itemId</td>
        <td>Edits a item based on their id</td>
      </tr>
      <tr>
        <td> Give item a discount </td>
        <td>/giveDiscount/:itemId/:discountPercentage</td>
        <td>Apply a discount to a specific item based on its ID and a discount percentage (an integer value).</td>
      </tr>
  <tr>
        <td> Add a Category to an Item </td>
  	<td>/addCategory/:itemId/:categoryId</td>
  	<td>Adds a category to an item based on their ID</td>
    </tr>
  	<td>Remove a Category from an Item</td>
  	<td>/removeCategory/:itemId/:categoryId</td>
      <td>Removes a category from an item based on its ID</td>
  </tr>
    </table>
  	<p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Itens/ItemServiceimpl.java">ItemServiceImpl.class</p>

  <h1 id="Category">Category</h1>
  http://localhost:8080/api/category

  <h2>Category Properties:</h2>
    <ul>
      <li>categoryId</li>
      <li>Name</li>
      <li>Itens (items that are in this category) </li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Category/Category.java">Category.class</p>
  <h2>Category Methods:</h2>
    <table>
      <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Description</th>
      </tr>
      <tr>
        <td> Get All Categories </td>
  			<td>/getAll</td>
  			<td>Returns all categories</td>
      </tr>
      <tr>
        <td> Get Category By Id </td>
  			<td>/getCategoryById/:categoryId</td>
  			<td>Returns a category based on its ID</td>
      </tr>
      <tr>
        <td> Get Category By Name </td>
        <td>/getByName/:name</td>
        <td>Returns a list of category based on their name</td>
      </tr>
      <tr>
        <td> Post Category </td>
        <td>/post</td>
        <td>Post an Category</td>
      </tr>
      <tr>
        <td> Delete Category By Id </td>
        <td>/delete/:categoryId</td>
        <td>Deletes a category based on their id</td>
      </tr>
      <tr>
        <td> Put Category </td>
        <td>/put/:categoryId</td>
        <td>Edits a category based on their id</td>
      </tr>
    </table>
  	<p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Category/CategoryServiceImpl.java">CategoryServiceImpl.class</p>

<h1 id="order-item">Order Item</h1>
	http://localhost:8080/api/orderItem
	<p>This class serves as a shopping cart item, receiving an Item and a quantity. It also has a function that returns the total price (serving as an aid in posting the Order).</p>
  
  <h2>Order Item Properties:</h2>
    <ul>
      <li>orderItemId</li>
      <li>item </li>
      <li>quantity </li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/OrderItem/OrderItem.java">OrderItem.class</p>
	
<h2>Order Item Methods:</h2>
    <table>
      <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Description</th>
      </tr>
      <tr>
        <td> Get All Order Item</td>
				<td>/getAll</td>
				<td>Returns all order Item</td>
      </tr>
      <tr>
        <td> Post Order Item </td>
        <td>/post/:itemId</td>
        <td>Posts an OrderItem, requires an item id in the path variable and quantity in the request body</td>
      </tr>
      <tr>
        <td> Delete Order Item By Id </td>
        <td>/deleteById/:orderItemId</td>
        <td>Deletes a order item based on their id</td>
      </tr>
      <tr>
        <td> Change the item quantity on the Order Item </td>
        <td>/changeQuantity/:orderItemId</td>
        <td>Changes the quantity property of an orderItem, received through a requestBody</td>
      </tr>
    </table>
<p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/OrderItem/OrdemItemServiceImpl.java">orderItemServiceImpl.class</p>

<h1 id="order">Order </h1>
	http://localhost:8080/api/order
	<p>This class is the order made, it retrieves the ids of the items in the cart to form the order information, it also requires a user</p>
  
  <h2>Order Properties:</h2>
    <ul>
      <li>OrderId</li>
      <li>User </li>
      <li>ItensId (List of OrderItem IDs) </li>
			<li>Itens </li>
			<li>status (Accepts the values PROCESSING, PAID, CANCELLED)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Order/Order.java">Order.class</p>
	<h2>Order Methods:</h2>
    <table>
      <tr>
        <th>Method</th>
        <th>URL</th>
        <th>Description</th>
      </tr>
      <tr>
        <td> Get All Order</td>
				<td>/getAll</td>
				<td>Returns all orders</td>
      </tr>
      <tr>
        <td> Post Order</td>
        <td>/post/:userId</td>
        <td>Posting an Order requires a user ID. During the post, it removes the items from the stock and sets the order status to processing</td>
      </tr>
      <tr>
        <td> Cancel Order By Id </td>
        <td>/cancelOrder/:orderId</td>
        <td>Sets the status of an Order to CANCELLED, returning the items to the stock. Performs this operation based on its ID</td>
      </tr>
      <tr>
        <td> Paid Order By Id </td>
        <td>/paidOrder/:orderId</td>
        <td>Changes the status of an Order to PAID. If the order has a CANCELLED status, a custom exception will be generated</td>
      </tr>
    </table>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Order/OrderServiceImpl.java">orderServiceImpl.class</p>
