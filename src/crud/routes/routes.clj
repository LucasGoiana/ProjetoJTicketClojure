(ns crud.routes.routes
  (:use crud.functions.profile.functions)
  (:use crud.functions.users.functions))

(def general-routes
    #{
      ;Rotas Pertinentes a Perfil
      ["/perfil" :post make-profile :route-name :criar-perfil],
      ["/perfil" :get read-profiles :route-name :ler-perfis],
      ["/perfil/:id" :get read-profile-by-id :route-name :ler-perfil],

      ;Rotas Pertinentes a Usuário
      ["/usuario" :post make-user :route-name :criar-usuario],
      ["/usuario/:id" :put update-user :route-name :editar-usuario],
      ["/usuario" :get read-users :route-name :ler-usuarios],
      ["/usuario/:id" :get read-user-by-id :route-name :ler-usuario]
      ["/usuario/:id" :delete delete-user-by-id :route-name :deletar-usuario]

      ;Rotas Pertinentes a Status
      ["/status" :post make-status :route-name :criar-perfil],
      ["/status" :get read-status :route-name :ler-perfis],
      ["/status/:id" :get read-status-by-id :route-name :ler-perfil],

      })