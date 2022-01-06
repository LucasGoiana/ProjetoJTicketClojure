(ns crud.database.tickets.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Ticket
(defn make [idUser idStatus title description ] (query/insert! ds :ticket
              {:idUsuario idUser :idStatus idStatus :Titulo title :Descricao description :DataCriacao (java.util.Date.) :DataModificacao (java.util.Date.)}))

;Atualizar Ticket pelo ID
(defn update-by-id [id idUser idStatus title description slug]  (query/update! ds :ticket
             {:idUsuario idUser :idStatus idStatus :Titulo title :Descricao description :Slug slug :DataModificacao (java.util.Date.)}{:idTicket id }))

; Pega o ultimo ticket para o slug
(defn get-id-to-slug [request] (read-as-local) (query/query ds ["SELECT idTicket FROM ticket order by idTicket desc limit 1"]))

; Inserir o slug
(defn insert-slug [id slug]  (query/update! ds :ticket
    { :Slug slug :DataModificacao (java.util.Date.)}{:idTicket id }))

; Buscar Lista de Tickets
(defn readAll [request] (read-as-local) (query/query ds ["SELECT t.idTicket, t.Titulo, t.descricao, t.DataModificacao, u.Nome NomeUsuario, s.Nome NomeStatus FROM  ticket t INNER JOIN usuario u ON t.idUsuario = u.idUsuario INNER JOIN status s ON t.idStatus = s.idStatus "]))

; Buscar um Ticket pelo id
(defn read-by-id [id]  (jdbc/execute-one! ds ["SELECT t.idTicket, t.Titulo, t.descricao, t.DataModificacao, u.Nome NomeUsuario, s.Nome NomeStatus FROM  ticket t INNER JOIN usuario u ON t.idUsuario = u.idUsuario INNER JOIN status s ON t.idStatus = s.idStatus WHERE t.idTicket = ?  " id]))

;Deletar Ticket pelo ID
(defn delete [id]  (query/delete! ds :ticket { :idTicket id}))