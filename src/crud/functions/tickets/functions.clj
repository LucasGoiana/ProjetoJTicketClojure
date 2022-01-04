(ns crud.functions.tickets.functions
  (:use
    [crud.database.tickets.queries],
    [crud.helpers.helpers]),
  (:require
    [clojure.data.json :as json],
    [clj-commons.digest :as digest],
    [config.core :refer [env]]))

(defn make-ticket [request]
  (let [nm (:json-params request)],
    (let [idUser (nm :idUser)],
      (let [idStatus (nm :idStatus)],
      (let [title (nm :title)],
        (let [description (nm :description)],
            (let [slug title],
              (make idUser idStatus title description slug)
              {:status 201
               :headers header-modified
               :body (make-json {:msg "Cadastrado com Sucesso!"})})))))))

;(defn update-ticket [request]
;  (let [nm (:json-params request)],
;    (let [id (get-in request [:path-params :id])],
;      (let [name (nm :name)],
;        (let [email (nm :email)],
;          (let [password (digest/md5 (nm :password)) ],
;            (let [slug name],
;              (update-by-id id name email password slug)
;              {:status 200
;               :headers header-modified
;               :body (make-json {:msg "Editado com Sucesso!"})})))))))
;
;(defn  read-tickets [request]
;  (let [response (readAll [request])]
;    {:status 200
;     :headers header-modified
;     :body (json/write-str response )}))
;
;(defn  read-ticket-by-id [request]
;  (let [id (get-in request [:path-params :id])],
;    (let [response  (read-by-id id)]
;      {:status 200
;       :headers header-modified
;       :body (json/write-str response )})))
;
;
;(defn  delete-ticket-by-id [request]
;  (let [id (get-in request [:path-params :id])],
;    (delete id)
;    {:status 200
;     :headers header-modified
;     :body (make-json {:msg "Usuário foi deletado com Sucesso!"})}))
