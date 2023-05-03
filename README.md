## User

http://localhost:8080/api/user/

### User tem as Propriedades :

- UserId
- Name
- Email
- WhisList ( Uma Lista de itens que o usario deseja comprar depois )

### User Metodos

- /getAll (Retorna todos os usuarios)
- /getUserById/:userId (Retorna um usuario apartir do id dele)
- /getByEmail/:userId(Retorna uma lista dos usuarios com o Email colocado)
- /postUser ( Posta um user )
- /put/:userId (Edita um usuario apartir da Id)
- /delete/:userId (deleta um usuario apartir do id)
- /addItemToWishList/user/:userId/item/:itemId (Adiciona um item a whislist de um usario, apartir do id do user e do id do item)
- /removeItemFromWishlist/user/:userId/item/:itemId (remove um item a whislist de um usario, apartir do id do user e do id do item)

## Item

http://localhost:8080/api/item

### Item tem as Propriedades :

- ItemId
- Name
- Price
- Stock
- Category ( Lista de categorias a qual o objeto pertence )
- Users ( Lista de usuarios que botaram esse item no whishlist )

### Item Metodos

- getAll (Retorna todos os item)
- getById (Retorna um item apartir do id dele)
- getByName ( Retorna restaurant apartir do nome dele )
- filter/priceLimit( Retorna os items apartir de um preço limite )
- post( Posta um Item )
- delete (Deleta um item pela Id dele)
- put (Edita um Item apartir da Id)
- giveDiscount ( da desconto a um item apartir do id dele, recebe tambem a % do discount)
- addCategory ( adiciona uma categoria apartir do id da categoria)
- removeCategory ( Remove uma categorua apartir do id da categoria )post

## Category

http://localhost:8080/api/category

### Category tem as Propriedades :

- categoryId
- name
- itens ( itens que estao nessas categorias )

### Category Metodos

- getAll (Retorna todas as categorys)
- getByName (Retorna categorias com nome similar ao passado)
- post( Posta uma category)
- deleteById ( Deleta um category apartir da id dela )
- putCategoryById ( Edita uma categoria apartir do id, so muda o nome)

## Order Item

Caminho para o arquivo no github

Essa class serve como um Item do carrinho de compra, recebendo um Item e uma quantity, ela tambem possui uma função que retorna o total ( servindo de auxilo na postagem da Order) 

### OrderItem tem as Propriedades :

- orderItemId
- item
- itens ( itens que estao nessas categorias )

### OrderItem Metodos

http://localhost:8080/api/orderItem

- /getAll (Retorna todos os OrderItem)
- /post/:itemId ( Posta um OrderItem, precisa de um id do item e a quantity no body, durante o post é checado se a quantidade de um Item for maior q o stock é jogada uma exceção NotEnoughItemsException)
- /deleteById/:orderItemId ( Deleta um OrderItem apartir da id dele )
- /changeQuantity/:orderItemId ( Muda o propriedade quantity de um orderItem, recebe isso atras de um requestBody

## Order

Caminho para o arquivo no github

Essa classe é o pedido feito, ela pega os id do itens do carrinho para formar as informaçoes do pedido, ela tambem precisa de usuario

### Order tem as Propriedades :

- orderId
- user ( User relacionado com a order )
- idItens ( recebe uma lista de id OrderItem para fazer a propriedade itens) ]
- itens ( lista com os item e quantidade do carrinho )
- status ( Aceita os valores PROCESSING,PAID,CANCELLED )

### Order Metodos CREATE THE CHANGE STATUS AND CANCELLED

http://localhost:8080/api/order

- /getAll (Retorna todas as Order)
- /post/:itemId ( Posta uma Order precisa de um id do usuario, durante o post retira os items do stock e define a orderStatus como processing )
- /cancelOrder/:orderId( Define o status de uma Order para CANCELLED, com isso o items são devolvidos ao stock, faz essa operação apartir da id dele )
- /paidOrder/:orderId ( Muda o status uma Order para PAID, se a order tiver o status de CANCELLED ira ser gerada uma exeção personalizada )
