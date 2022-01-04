(ns crud.functions.users.functions
  (:use
    [crud.database.users.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json],
    [clj-commons.digest :as digest],
    [config.core :refer [env]]))

(defn make-user [request]
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

(defn update-user [request]
  (let [nm (:json-params request)],
    (let [id (get-in request [:path-params :id])],
      (let [name (nm :name)],
        (let [email (nm :email)],
          (let [password (digest/md5 (nm :password)) ],
            (let [slug name],
              (atualizar id name email password slug)
              {:status 200
               :headers header-modified
               :body (make-json {:msg "Editado com Sucesso!"})})))))))

(defn  read-users [request]
  (let [response (ler [request])]
    {:status 200
     :headers header-modified
     :body (json/write-str response )}))

(defn  read-user-by-id [request]
  (let [id (get-in request [:path-params :id])],
    (let [response  (lerPorId id)]
    {:status 200
     :headers header-modified
     :body (json/write-str response )})))


(defn  delete-user-by-id [request]
  (let [id (get-in request [:path-params :id])],
      (deletar id)
      {:status 200
       :headers header-modified
       :body (make-json {:msg "Usuário foi deletado com Sucesso!"})}))