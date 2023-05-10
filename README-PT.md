## Repositório Web Store Spring Boot

Este repositório contém uma loja virtual simples em Spring Boot.

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
