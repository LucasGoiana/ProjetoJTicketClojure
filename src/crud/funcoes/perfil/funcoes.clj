(ns crud.funcoes.perfil.funcoes
  (:use
    [crud.database.perfil.querys]),
  (:require
    [clojure.data.json :as json]
    [io.pedestal.http :as http]))

(def header-modified {"Access-Control-Allow-Origin"  "*"  "Access-Control-Allow-Headers" "'Access-Control-Allow-Headers: Origin, X-Auth-Token'" "Content-Type" "application/json"} )
(defn make-json [m]
  (json/write-str m ))


(defn criar-perfil [request]
           (let [nm (:json-params request)],
             (let [nome-perfil (nm :nome)]
             (inserir nome-perfil)
             {:status 200
              :headers header-modified
              :body (make-json {:Status "Show de bola"}) })))


(defn ler-perfis [request]
    (let [response (ler request)]
       {:status 200
        :headers header-modified
        :body (json/write-str response )}))

(defn ler-perfil [request]

  (let [response  (lerPorId (get-in request [:path-params :id]))]
      {:status 200
        :headers header-modified
           :body (json/write-str response )}))

