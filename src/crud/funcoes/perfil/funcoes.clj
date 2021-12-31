(ns crud.funcoes.perfil.funcoes
  (:use
    [crud.database.perfil.querys]),
  (:require
    [clojure.data.json :as json]
    [io.pedestal.http :as http]))


(defn make-json [m]
  m  )


(defn criar-perfil [request]
           (let [nm (:json-params request)],
             (let [nome-perfil (nm :nome)]
             (inserir nome-perfil)
             {:status 200
              :headers {"Access-Control-Allow-Origin"  "*"  "Access-Control-Allow-Headers" "'Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token'"}
              :body (make-json {"Status" "Show de bola"}) })))


(defn ler-perfis [request]
    (let [response (ler request)]
       {:status 200
        :headers {
                  "Access-Control-Allow-Origin"  "*"
                  "Access-Control-Allow-Headers"
                  "'Access-Control-Allow-Headers: Origin, Content-Type, X-Auth-Token'"
                  "Content-Type" "application/json"}
        :body (json/write-str response )}))
