(ns crud.database.users.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Usuário
(defn make [id-profile name email password] (query/insert! ds :usuario
              {:idPerfil id-profile :Nome name :Email email :Senha password :DataModficacao (java.util.Date.)}))

;Atualizar Usuário pelo ID
(defn update-by-id [id name email password slug] (query/update! ds :usuario
        {:Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.)} {:idUsuario id }))
;Buscar ultimo usuario para o slug
(defn get-id-to-slug [request] (read-as-local)
      (query/query ds ["SELECT idUsuario FROM usuario order by idUsuario desc limit 1"]))

; Inserir o slug
(defn insert-slug [id slug]  (query/update! ds :usuario
           { :Slug slug :DataModficacao (java.util.Date.)}{:idUsuario id }))
; Buscar Lista de Usuários
(defn readAll [request] (read-as-local) (query/query ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil"]))

; Buscar um Usuário pelo id
(defn read-by-id [id]  (jdbc/execute-one! ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil where u.idUsuario = ? "  id]))

;Deletar Ticket pelo ID
(defn deleteTicket [id]  (query/delete! ds :ticket { :idUsuario id}))

;Deletar Usuário pelo ID
(defn delete [id]  (query/delete! ds :usuario { :idUsuario id}))