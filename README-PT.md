## Repositório Web Store Spring Boot

Este repositório contém uma loja virtual simples em Spring Boot.
Você vai precisar de um banco de dados MySql chamado "web_application" para funcionar.

- [Usuário](#user)
- [Item](#item)
- [Category](#category)
- [Order Item](#order-item)
- [Order](#order)
- [Postman collection](Web Store Spring.postman_collection.json)
<h1 id="user">Usuário</h1>
  http://localhost:8080/api/user/
  <h2>Propriedades do Usuário:</h2>
    <ul>
      <li>UserId</li>
      <li>Nome</li>
      <li>Email</li>
      <li>Lista de Desejos (Uma lista de itens que o usuário deseja comprar posteriormente)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/User/User.java">User.class</p>
		<h2>Métodos do Usuário:</h2>
    <table>
      <tr>
        <th>Método</th>
        <th>URL</th>
        <th>Descrição</th>
      </tr>
      <tr>
        <td> Obter Todos os Usuários </td>
        <td>/getAll</td>
        <td>Retorna todos os usuários</td>
      </tr>
      <tr>
        <td> Obter Usuário Por Id </td>
        <td>/getById/:userId</td>
        <td>Retorna um usuário com base em seu ID</td>
      </tr>
      <tr>
        <td> Obter Usuário Por Email </td>
        <td>/getByEmail/:email</td>
        <td>Retorna uma lista de usuários com o email especificado</td>
      </tr>
      <tr>
        <td> Postar Usuário </td>
        <td>/post</td>
        <td>Publica um novo usuário</td>
      </tr>
      <tr>
        <td> Editar Usuário </td>
        <td>/put/:userId</td>
        <td>Edita um usuário com base em seu ID</td>
      </tr>
      <tr>
        <td> Excluir Usuário Por Id </td>
        <td>/delete/:userId</td>
        <td>Exclui um usuário com base em seu ID</td>
      </tr>
      <tr>
        <td> Adicionar Item à Lista de Desejos do Usuário </td>
        <td>/addItemToWishList/user/:userId/item/:itemId</td>
        <td>Adiciona um item à lista de desejos de um usuário com base em seu ID e o ID do item</td>
      </tr>
      <tr>
        <td> Remover Item da Lista de Desejos do Usuário </td>
        <td>/removeItemFromWishlist/user/:userId/item/:itemId</td>
        <td>Remove um item da lista de desejos de um usuário com base em seu ID e o ID do item</td>
      </tr>
    </table>
	<p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/User/UserServiceImpl.java">UserServiceImpl.java</p>
    <h1 id="item">Item</h1>
	http://localhost:8080/api/item
  <h2>Propriedades do Item:</h2>
    <ul>
      <li>ItemId</li>
      <li>Nome</li>
      <li>Preço</li>
      <li>Estoque</li>
      <li>Categoria (Lista de categorias às quais o item pertence)</li>
      <li>Usuários (Lista de usuários que adicionaram este item à sua lista de desejos)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Itens/Item.java">Item.class</p>
	<h2>Métodos do Item:</h2>
    <table>
      <tr>
        <th>Método</th>
        <th>URL</th>
        <th>Descrição</th>
      </tr>
      <tr>
        <td> Obter Todos os Itens </td>
        <td>/getAll</td>
        <td>Retorna todos os itens</td>
      </tr>
      <tr>
        <td> Obter Item Por Id </td>
        <td>/getById/:itemId</td>
        <td>Retorna um item com base em seu id</td>
      </tr>
      <tr>
        <td> Obter Item Por Nome </td>
        <td>/getByName/:name</td>
        <td>Retorna uma lista de itens com base em seu nome</td>
      </tr>
      <tr>
        <td> Filtrar Itens Por Preço </td>
        <td>/filter/priceLimit/:priceLimit</td>
        <td>Retorna uma lista de itens com base em um limite de preço</td>
      </tr>
      <tr>
        <td> Publicar Item </td>
        <td>/post</td>
        <td>Publica um item</td>
      </tr>
      <tr>
        <td> Excluir Item Por Id </td>
        <td>/delete/:itemId</td>
        <td>Exclui um item com base em seu id</td>
      </tr>
      <tr>
        <td> Editar Item </td>
        <td>/put/:itemId</td>
        <td>Edita um item com base em seu id</td>
      </tr>
      <tr>
        <td> Dar desconto no item </td>
        <td>/giveDiscount/:itemId/:discountPercentage</td>
        <td>Aplica um desconto em um item específico com base em seu ID e uma porcentagem de desconto (um valor inteiro).</td>
      </tr>
	<tr>
        <td> Adicionar uma Categoria a um Item </td>
		<td>/addCategory/:itemId/:categoryId</td>
		<td>Adiciona uma categoria a um item com base em seu ID</td>
    </tr>
		<td>Remover uma Categoria de um Item</td>
		<td>/removeCategory/:itemId/:categoryId</td>
	    <td>Remove uma categoria de um item com base em seu ID</td>
	</tr>
    </table>
    <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Itens/ItemServiceimpl.java">ItemServiceImpl.class</p>
	<h1 id="Category">Categoria</h1>
	http://localhost:8080/api/categoria
  <h2>Propriedades da Categoria:</h2>
    <ul>
      <li>Id da categoria</li>
      <li>Nome</li>
      <li>Itens (itens que estão nesta categoria)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Category/Category.java">Categoria.class</p>
	<h2>Métodos da Categoria:</h2>
    <table>
      <tr>
        <th>Método</th>
        <th>URL</th>
        <th>Descrição</th>
      </tr>
      <tr>
        <td> Obter Todas as Categorias </td>
				<td>/getAll</td>
				<td>Retorna todas as categorias</td>
      </tr>
      <tr>
        <td> Obter Categoria Por Id </td>
				<td>/getCategoryById/:categoryId</td>
				<td>Retorna uma categoria com base em seu ID</td>
      </tr>
      <tr>
        <td> Obter Categoria Por Nome </td>
        <td>/getByName/:name</td>
        <td>Retorna uma lista de categorias com base em seu nome</td>
      </tr>
      <tr>
        <td> Postar Categoria </td>
        <td>/post</td>
        <td>Postar uma Categoria</td>
      </tr>
      <tr>
        <td> Deletar Categoria Por Id </td>
        <td>/delete/:categoryId</td>
        <td>Deleta uma categoria com base em seu id</td>
      </tr>
      <tr>
        <td> Editar Categoria </td>
        <td>/put/:categoryId</td>
        <td>Edita uma categoria com base em seu id</td>
      </tr>
    </table>
		<p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Category/CategoryServiceImpl.java">CategoryServiceImpl.class</p>
    <h1 id="order-item">Item do Pedido</h1>
	http://localhost:8080/api/orderItem
	<p>Esta classe serve como um item do carrinho de compras, recebendo um Item e uma quantidade. Ele também tem uma função que retorna o preço total (servindo como um auxílio para postar o Pedido).</p>
  <h2>Propriedades do Item do Pedido:</h2>
    <ul>
      <li>orderItemId</li>
      <li>item </li>
      <li>quantity </li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/OrderItem/OrderItem.java">OrderItem.class</p>
<h2>Métodos do Item do Pedido:</h2>
    <table>
      <tr>
        <th>Método</th>
        <th>URL</th>
        <th>Descrição</th>
      </tr>
      <tr>
        <td>Obter todos os itens do pedido</td>
				<td>/getAll</td>
				<td>Retorna todos os itens do pedido</td>
      </tr>
      <tr>
        <td>Postar Item do Pedido</td>
        <td>/post/:itemId</td>
        <td>Publica um Item do Pedido, requer um ID do item na variável de caminho e quantidade no corpo da solicitação</td>
      </tr>
      <tr>
        <td>Excluir Item do Pedido por ID</td>
        <td>/deleteById/:orderItemId</td>
        <td>Exclui um item do pedido com base em seu ID</td>
      </tr>
      <tr>
        <td>Alterar a quantidade de item no Item do Pedido</td>
        <td>/changeQuantity/:orderItemId</td>
        <td>Altera a propriedade de quantidade de um item do pedido, recebido através de um requestBody</td>
      </tr>
    </table>
    <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/OrderItem/OrdemItemServiceImpl.java">orderItemServiceImpl.class</p>
    <h1 id="order">Ordem</h1>
	http://localhost:8080/api/order
	<p>Esta classe é responsável pela ordem feita, ela recupera os IDs dos itens no carrinho para formar as informações do pedido, também requer um usuário</p>
  <h2>Propriedades da Ordem:</h2>
    <ul>
      <li>ID da Ordem</li>
      <li>Usuário </li>
      <li>IDs dos Itens (Lista de IDs de Item de Ordem)</li>
			<li>Itens </li>
			<li>status (Aceita os valores PROCESSING, PAID, CANCELLED)</li>
    </ul>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Order/Order.java">Order.class</p>
	<h2>Métodos da Ordem:</h2>
    <table>
      <tr>
        <th>Método</th>
        <th>URL</th>
        <th>Descrição</th>
      </tr>
      <tr>
        <td> Obter Todas as Ordens</td>
				<td>/getAll</td>
				<td>Retorna todas as ordens</td>
      </tr>
      <tr>
        <td> Postar Ordem</td>
        <td>/post/:userId</td>
        <td>Postar uma ordem requer um ID de usuário. Durante o post, ele remove os itens do estoque e define o status do pedido como processamento</td>
      </tr>
      <tr>
        <td> Cancelar Ordem pelo ID </td>
        <td>/cancelOrder/:orderId</td>
        <td>Define o status de uma ordem para CANCELADO, devolvendo os itens ao estoque. Realiza essa operação com base em seu ID</td>
      </tr>
      <tr>
        <td> Pagar Ordem pelo ID </td>
        <td>/paidOrder/:orderId</td>
        <td>Altera o status de uma ordem para PAGO. Se a ordem tiver um status CANCELADO, será gerada uma exceção personalizada</td>
      </tr>
    </table>
  <p><a href="https://github.com/ThiagoDambroski/Spring-Boot-Project/blob/main/src/main/java/com/dambroski/webStoreProject/Order/OrderServiceImpl.java">orderServiceImpl.class</p>
