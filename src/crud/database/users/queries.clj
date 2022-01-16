(ns crud.database.users.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Usuário
(defn make [idProfile name email password] (query/insert! ds :user
              {:idProfile idProfile :name name :email email :password password}))

;Atualizar Usuário pelo ID
(defn update-by-id [id name email password slug] (query/update! ds :user
        {:name name :email email :password password :slug slug } {:idUser id }))

;Buscar ultimo usuario para o slug
(defn get-id-to-slug [request] (read-as-local)
      (query/query ds ["SELECT idUser FROM user order by idUser desc limit 1"]))

; Inserir o slug
(defn insert-slug [id slug]  (query/update! ds :user
           {:slug slug}{:idUser id }))

; Buscar Lista de Usuários
(defn readAll [] (read-as-local) (query/query ds ["SELECT u.idUser, u.name, u.email, u.password, p.name as profile FROM user u INNER JOIN profile p ON u.idProfile = p.idProfile"]))

; Buscar um Usuário pelo id
(defn read-by-id [id]  (jdbc/execute-one! ds ["SELECT u.idUser,  u.name, u.email, u.password, p.name as profile FROM user u INNER JOIN profile p ON u.idProfile = p.idProfile where u.idUser = ? "  id]))

;Deletar Ticket pelo ID
(defn delete-ticket [id]  (query/delete! ds :ticket { :idUser id}))

;Deletar Usuário pelo ID
(defn delete [id]  (query/delete! ds :user { :idUser id}))

;Fazer login

(defn login [email password] (jdbc/execute-one! ds ["SELECT u.idUser,  u.idProfile FROM user u where u.email = ? and u.password = ? "  email password]))

(defn validate-email [email] (jdbc/execute-one! ds ["SELECT COUNT(*) as quantityEmail FROM user u where u.email = ? "  email ]))