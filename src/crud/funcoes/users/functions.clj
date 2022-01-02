(ns crud.funcoes.users.functions
  (:use
    [crud.database.users.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json],
    [clj-commons.digest :as digest],
    [config.core :refer [env]]))

(defn criar-usuario [request]
  (let [nm (:json-params request)],
     (let [id-profile (nm :idProfile)],
       (let [name (nm :name)],
         (let [email (nm :email)],
           (let [password (digest/md5 (nm :password)) ],
             (let [slug name],
      (inserir id-profile name email password slug)
      {:status 201
       :headers header-modified
       :body (make-json {:msg "Cadastrado com Sucesso!"})})))))))