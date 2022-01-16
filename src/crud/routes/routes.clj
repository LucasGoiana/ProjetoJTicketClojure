(ns crud.routes.routes
  (:use crud.functions.profile.functions,
        crud.functions.users.functions,
        crud.functions.status.functions,
        crud.functions.tickets.functions,
        crud.functions.login.functions))

(def generalRoutes
    #{
      ;Rotas Pertinentes a Perfil
      ["/perfil" :post make-profile :route-name :make-profile ],
      ["/perfil" :get read-profiles :route-name :read-profiles],
      ["/perfil/:id" :get read-profile-by-id :route-name :read-profile-by-id],

      ;Rotas Pertinentes a Usuário
      ["/usuario" :post make-user :route-name :make-user],
      ["/usuario/:id" :put update-user :route-name :update-user],
      ["/usuario" :get read-users :route-name :read-users],
      ["/usuario/:id" :get read-user-by-id :route-name :read-user-by-id]
      ["/usuario/:id" :delete delete-user-by-id :route-name :delete-user-by-id]

      ;Rotas Pertinentes a Status
      ["/status" :post make-status :route-name :make-status],
      ["/status" :get read-status :route-name :read-status],
      ["/status/:id" :get read-status-by-id :route-name :read-status-by-id],

      ;Rotas Pertinentes a Ticket
      ["/ticket" :post make-ticket :route-name :make-ticket],
      ["/ticket/:id" :put update-ticket :route-name :update-ticket],
      ["/ticket/:id/status/:id2" :put update-status-ticket :route-name :update-status-ticket],
      ["/ticket" :get read-tickets :route-name :read-tickets],
      ["/ticket/:id" :get read-ticket-by-id :route-name :read-ticket-by-id]
      ["/ticket/:id" :delete delete-ticket-by-id :route-name :delete-ticket-by-id]

      ;Login
      ["/login" :post make-login :route-name :make-login],

      })