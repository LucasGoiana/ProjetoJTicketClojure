(ns crud.database.users.queries
  (:use
    [crud.database.connection],
    [next.jdbc.date-time]),
  (:require
    [next.jdbc.sql :as query],
    [next.jdbc :as jdbc]))

; Inserir Usuário
(defn inserir [id-profile name email password slug] (query/insert! ds :usuario
              {:idPerfil id-profile :Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.)}))

;Atualizar Usuário pelo ID
(defn atualizar [id name email password slug] (query/update! ds :usuario
              {:Nome name :Email email :Senha password :Slug slug  :DataModficacao (java.util.Date.)} {:idUsuario id }))

; Buscar Lista de Usuários
(defn ler [request] (read-as-local) (query/query ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil"]))

; Buscar um Usuário pelo id
(defn lerPorId [id]  (jdbc/execute-one! ds ["SELECT u.Nome, u.Email, u.senha, p.Nome as NomePerfil FROM usuario u INNER JOIN perfil p ON u.idPerfil = p.idPerfil where u.idUsuario = ? "  id] ))

;Deletar Usuário pelo ID
(defn deletar [id]  (query/delete! ds :usuario { :idUsuario id}))