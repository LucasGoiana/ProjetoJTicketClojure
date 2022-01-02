(ns crud.routes.routes
  (:use crud.funcoes.perfil.functions)
  (:use crud.funcoes.users.functions))

(def general-routes
    #{["/perfil" :post criar-perfil :route-name :criar-perfil],
      ["/perfil" :get ler-perfis :route-name :ler-perfis],
      ["/perfil/:id" :get ler-perfil :route-name :ler-perfil],


      ["/usuario" :post criar-usuario :route-name :criar-usuario],
      })