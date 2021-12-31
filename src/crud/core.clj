(ns crud.core
  (:use crud.funcoes.perfil.funcoes),
  (:require
    [io.pedestal.http.route :as route],
    [io.pedestal.http.body-params :as body-params],
    [io.pedestal.http :as http])
 )

(def routes (route/expand-routes
    #{["/perfil"  :post criar-perfil :route-name :criar-perfil],
      ["/perfil"  :get ler-perfil :route-name :ler-perfil]}))

(def service-map
  (-> {::http/routes routes
       ::http/port 9999
       ::http/type :jetty
       ::http/join? false}
      http/default-interceptors
      (update ::http/interceptors conj (body-params/body-params))))

(http/start (http/create-server service-map))