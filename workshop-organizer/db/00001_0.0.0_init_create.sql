--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2

-- Started on 2022-06-28 15:37:43

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3330 (class 0 OID 0)
-- Dependencies: 3
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 209 (class 1259 OID 16385)
-- Name: notion_model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notion_model (
    name character varying(255) NOT NULL
);


ALTER TABLE public.notion_model OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16391)
-- Name: workshop_model; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workshop_model (
    id integer NOT NULL,
    name character varying(255)
);


ALTER TABLE public.workshop_model OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16390)
-- Name: workshop_model_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workshop_model_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workshop_model_id_seq OWNER TO postgres;

--
-- TOC entry 3331 (class 0 OID 0)
-- Dependencies: 210
-- Name: workshop_model_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.workshop_model_id_seq OWNED BY public.workshop_model.id;


--
-- TOC entry 212 (class 1259 OID 16397)
-- Name: workshop_model_notions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workshop_model_notions (
    workshop_model_id integer NOT NULL,
    notions_name character varying(255) NOT NULL
);


ALTER TABLE public.workshop_model_notions OWNER TO postgres;

--
-- TOC entry 3175 (class 2604 OID 16394)
-- Name: workshop_model id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workshop_model ALTER COLUMN id SET DEFAULT nextval('public.workshop_model_id_seq'::regclass);


--
-- TOC entry 3321 (class 0 OID 16385)
-- Dependencies: 209
-- Data for Name: notion_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.notion_model VALUES ('Docker');


--
-- TOC entry 3323 (class 0 OID 16391)
-- Dependencies: 211
-- Data for Name: workshop_model; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.workshop_model VALUES (2, 'Workshop 1');


--
-- TOC entry 3324 (class 0 OID 16397)
-- Dependencies: 212
-- Data for Name: workshop_model_notions; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.workshop_model_notions VALUES (2, 'Docker');


--
-- TOC entry 3332 (class 0 OID 0)
-- Dependencies: 210
-- Name: workshop_model_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workshop_model_id_seq', 2, true);


--
-- TOC entry 3177 (class 2606 OID 16389)
-- Name: notion_model notion_model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notion_model
    ADD CONSTRAINT notion_model_pkey PRIMARY KEY (name);


--
-- TOC entry 3179 (class 2606 OID 16396)
-- Name: workshop_model workshop_model_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workshop_model
    ADD CONSTRAINT workshop_model_pkey PRIMARY KEY (id);


--
-- TOC entry 3180 (class 2606 OID 16400)
-- Name: workshop_model_notions fk1gva2ft1rtngr48163q5tie08; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workshop_model_notions
    ADD CONSTRAINT fk1gva2ft1rtngr48163q5tie08 FOREIGN KEY (notions_name) REFERENCES public.notion_model(name);


--
-- TOC entry 3181 (class 2606 OID 16405)
-- Name: workshop_model_notions fki6yxgb1j2hcgytt03w777cift; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workshop_model_notions
    ADD CONSTRAINT fki6yxgb1j2hcgytt03w777cift FOREIGN KEY (workshop_model_id) REFERENCES public.workshop_model(id);


-- Completed on 2022-06-28 15:37:43

--
-- PostgreSQL database dump complete
--

