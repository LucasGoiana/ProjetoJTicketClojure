(ns crud.database.tickets.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Ticket
(defn make [idUser idStatus title description slug] (query/insert! ds :ticket
              {:idUsuario idUser :idStatus idStatus :Titulo title :Descricao description :Slug slug :DataCriacao (java.util.Date.) :DataModificacao (java.util.Date.)}))

;Atualizar Ticket pelo ID
(defn update-by-id [id idUser idStatus title description slug]  (query/update! ds :usuario
             {:idUsuario idUser :idStatus idStatus :Titulo title :Descricao description :Slug slug :DataCriacao (java.util.Date.) :DataModificacao (java.util.Date.)}{:idTicket id }))

; Buscar Lista de Tickets
(defn readAll [request] (read-as-local) (query/query ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil"]))

; Buscar um Ticket pelo id
(defn read-by-id [id]  (jdbc/execute-one! ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil where u.idUsuario = ? "  id]))

;Deletar Ticket pelo ID
(defn delete [id]  (query/delete! ds :ticket { :idTicket id}))