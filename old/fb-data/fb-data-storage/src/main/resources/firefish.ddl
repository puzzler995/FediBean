--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4 (Ubuntu 15.4-2.pgdg22.04+1)
-- Dumped by pg_dump version 15.4 (Ubuntu 15.4-2.pgdg22.04+1)

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

--
-- Name: antenna_src_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.antenna_src_enum AS ENUM (
    'home',
    'all',
    'users',
    'list',
    'group',
    'instances'
);


ALTER TYPE public.antenna_src_enum OWNER TO postgres;

--
-- Name: log_level_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.log_level_enum AS ENUM (
    'error',
    'warning',
    'info',
    'success',
    'debug'
);


ALTER TYPE public.log_level_enum OWNER TO postgres;

--
-- Name: meta_sensitivemediadetection_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.meta_sensitivemediadetection_enum AS ENUM (
    'none',
    'all',
    'local',
    'remote'
);


ALTER TYPE public.meta_sensitivemediadetection_enum OWNER TO postgres;

--
-- Name: meta_sensitivemediadetectionsensitivity_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.meta_sensitivemediadetectionsensitivity_enum AS ENUM (
    'medium',
    'low',
    'high',
    'veryLow',
    'veryHigh'
);


ALTER TYPE public.meta_sensitivemediadetectionsensitivity_enum OWNER TO postgres;

--
-- Name: muted_note_reason_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.muted_note_reason_enum AS ENUM (
    'word',
    'manual',
    'spam',
    'other'
);


ALTER TYPE public.muted_note_reason_enum OWNER TO postgres;

--
-- Name: note_visibility_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.note_visibility_enum AS ENUM (
    'public',
    'home',
    'followers',
    'specified',
    'hidden'
);


ALTER TYPE public.note_visibility_enum OWNER TO postgres;

--
-- Name: notification_type_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.notification_type_enum AS ENUM (
    'follow',
    'mention',
    'reply',
    'renote',
    'quote',
    'reaction',
    'pollVote',
    'pollEnded',
    'receiveFollowRequest',
    'followRequestAccepted',
    'groupInvited',
    'app'
);


ALTER TYPE public.notification_type_enum OWNER TO postgres;

--
-- Name: page_visibility_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.page_visibility_enum AS ENUM (
    'public',
    'followers',
    'specified'
);


ALTER TYPE public.page_visibility_enum OWNER TO postgres;

--
-- Name: poll_notevisibility_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.poll_notevisibility_enum AS ENUM (
    'public',
    'home',
    'followers',
    'specified'
);


ALTER TYPE public.poll_notevisibility_enum OWNER TO postgres;

--
-- Name: relay_status_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.relay_status_enum AS ENUM (
    'requesting',
    'accepted',
    'rejected'
);


ALTER TYPE public.relay_status_enum OWNER TO postgres;

--
-- Name: user_profile_ffvisibility_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.user_profile_ffvisibility_enum AS ENUM (
    'public',
    'followers',
    'private'
);


ALTER TYPE public.user_profile_ffvisibility_enum OWNER TO postgres;

--
-- Name: user_profile_mutingnotificationtypes_enum; Type: TYPE; Schema: public; Owner: calckey
--

CREATE TYPE public.user_profile_mutingnotificationtypes_enum AS ENUM (
    'follow',
    'mention',
    'reply',
    'renote',
    'quote',
    'reaction',
    'pollVote',
    'pollEnded',
    'receiveFollowRequest',
    'followRequestAccepted',
    'groupInvited',
    'app'
);


ALTER TYPE public.user_profile_mutingnotificationtypes_enum OWNER TO postgres;

--
-- Name: note_replies(character varying, integer, integer); Type: FUNCTION; Schema: public; Owner: calckey
--

CREATE FUNCTION public.note_replies(start_id character varying, max_depth integer, max_breadth integer) RETURNS TABLE(id character varying)
    LANGUAGE sql
    AS $$
				SELECT DISTINCT id FROM (
					WITH RECURSIVE tree (id, ancestors, depth) AS (
						SELECT start_id, '{}'::VARCHAR[], 0
					UNION
						SELECT
							note.id,
							CASE
								WHEN note."replyId" = tree.id THEN tree.ancestors || note."replyId"
								ELSE tree.ancestors || note."renoteId"
							END,
							depth + 1
						FROM note, tree
						WHERE (
							note."replyId" = tree.id
							OR
							(
								-- get renotes but not pure renotes
								note."renoteId" = tree.id
								AND
								(
									note.text IS NOT NULL
									OR
									CARDINALITY(note."fileIds") != 0
									OR
									note."hasPoll" = TRUE
								)
							)
						) AND depth < max_depth
					)
					SELECT
						id,
						-- apply the limit per node
						row_number() OVER (PARTITION BY ancestors[array_upper(ancestors, 1)]) AS nth_child
					FROM tree
					WHERE depth > 0
				) AS recursive WHERE nth_child < max_breadth
			$$;


ALTER FUNCTION public.note_replies(start_id character varying, max_depth integer, max_breadth integer) OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: __chart__active_users; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__active_users (
    id integer NOT NULL,
    date integer NOT NULL,
    "unique_temp___registeredWithinWeek" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinWeek" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredWithinMonth" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinMonth" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredWithinYear" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinYear" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideWeek" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideWeek" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideMonth" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideMonth" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideYear" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideYear" smallint DEFAULT '0'::smallint NOT NULL,
    "___readWrite" smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___read character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___read smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___write character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___write smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart__active_users OWNER TO postgres;

--
-- Name: __chart__active_users_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__active_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__active_users_id_seq OWNER TO postgres;

--
-- Name: __chart__active_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__active_users_id_seq OWNED BY public.__chart__active_users.id;


--
-- Name: __chart__ap_request; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__ap_request (
    id integer NOT NULL,
    date integer NOT NULL,
    "___deliverFailed" integer DEFAULT 0 NOT NULL,
    "___deliverSucceeded" integer DEFAULT 0 NOT NULL,
    "___inboxReceived" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart__ap_request OWNER TO postgres;

--
-- Name: __chart__ap_request_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__ap_request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__ap_request_id_seq OWNER TO postgres;

--
-- Name: __chart__ap_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__ap_request_id_seq OWNED BY public.__chart__ap_request.id;


--
-- Name: __chart__drive; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__drive (
    id integer NOT NULL,
    date integer NOT NULL,
    "___local_incCount" integer DEFAULT '0'::bigint NOT NULL,
    "___local_incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___local_decCount" integer DEFAULT '0'::bigint NOT NULL,
    "___local_decSize" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_incCount" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_decCount" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_decSize" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__drive OWNER TO postgres;

--
-- Name: __chart__drive_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__drive_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__drive_id_seq OWNER TO postgres;

--
-- Name: __chart__drive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__drive_id_seq OWNED BY public.__chart__drive.id;


--
-- Name: __chart__federation; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__federation (
    id integer NOT NULL,
    date integer NOT NULL,
    "unique_temp___deliveredInstances" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___deliveredInstances" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___inboxInstances" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___inboxInstances" smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___stalled character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___stalled smallint DEFAULT '0'::smallint NOT NULL,
    ___sub smallint DEFAULT '0'::smallint NOT NULL,
    ___pub smallint DEFAULT '0'::smallint NOT NULL,
    ___pubsub smallint DEFAULT '0'::smallint NOT NULL,
    "___subActive" smallint DEFAULT '0'::smallint NOT NULL,
    "___pubActive" smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart__federation OWNER TO postgres;

--
-- Name: __chart__federation_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__federation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__federation_id_seq OWNER TO postgres;

--
-- Name: __chart__federation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__federation_id_seq OWNED BY public.__chart__federation.id;


--
-- Name: __chart__hashtag; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__hashtag (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_users integer DEFAULT 0 NOT NULL,
    ___remote_users integer DEFAULT 0 NOT NULL,
    unique_temp___local_users character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    unique_temp___remote_users character varying[] DEFAULT '{}'::character varying[] NOT NULL
);


ALTER TABLE public.__chart__hashtag OWNER TO postgres;

--
-- Name: __chart__hashtag_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__hashtag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__hashtag_id_seq OWNER TO postgres;

--
-- Name: __chart__hashtag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__hashtag_id_seq OWNED BY public.__chart__hashtag.id;


--
-- Name: __chart__instance; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__instance (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___requests_failed smallint DEFAULT '0'::bigint NOT NULL,
    ___requests_succeeded smallint DEFAULT '0'::bigint NOT NULL,
    ___requests_received smallint DEFAULT '0'::bigint NOT NULL,
    ___notes_total integer DEFAULT '0'::bigint NOT NULL,
    ___notes_inc integer DEFAULT '0'::bigint NOT NULL,
    ___notes_dec integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    ___users_total integer DEFAULT '0'::bigint NOT NULL,
    ___users_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___users_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___following_total integer DEFAULT '0'::bigint NOT NULL,
    ___following_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___following_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___followers_dec smallint DEFAULT '0'::bigint NOT NULL,
    "___drive_totalFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_incFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_incUsage" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_decFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_decUsage" integer DEFAULT '0'::bigint NOT NULL,
    "___notes_diffs_withFile" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart__instance OWNER TO postgres;

--
-- Name: __chart__instance_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__instance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__instance_id_seq OWNER TO postgres;

--
-- Name: __chart__instance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__instance_id_seq OWNED BY public.__chart__instance.id;


--
-- Name: __chart__network; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__network (
    id integer NOT NULL,
    date integer NOT NULL,
    "___incomingRequests" integer DEFAULT '0'::bigint NOT NULL,
    "___outgoingRequests" integer DEFAULT '0'::bigint NOT NULL,
    "___totalTime" integer DEFAULT '0'::bigint NOT NULL,
    "___incomingBytes" integer DEFAULT '0'::bigint NOT NULL,
    "___outgoingBytes" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__network OWNER TO postgres;

--
-- Name: __chart__network_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__network_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__network_id_seq OWNER TO postgres;

--
-- Name: __chart__network_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__network_id_seq OWNED BY public.__chart__network.id;


--
-- Name: __chart__notes; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__notes (
    id integer NOT NULL,
    date integer NOT NULL,
    ___local_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_inc integer DEFAULT '0'::bigint NOT NULL,
    ___local_dec integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    ___remote_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_inc integer DEFAULT '0'::bigint NOT NULL,
    ___remote_dec integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    "___local_diffs_withFile" integer DEFAULT 0 NOT NULL,
    "___remote_diffs_withFile" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart__notes OWNER TO postgres;

--
-- Name: __chart__notes_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__notes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__notes_id_seq OWNER TO postgres;

--
-- Name: __chart__notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__notes_id_seq OWNED BY public.__chart__notes.id;


--
-- Name: __chart__per_user_drive; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__per_user_drive (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    "___totalCount" integer DEFAULT '0'::bigint NOT NULL,
    "___totalSize" integer DEFAULT '0'::bigint NOT NULL,
    "___incCount" smallint DEFAULT '0'::bigint NOT NULL,
    "___incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___decCount" smallint DEFAULT '0'::bigint NOT NULL,
    "___decSize" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__per_user_drive OWNER TO postgres;

--
-- Name: __chart__per_user_drive_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__per_user_drive_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__per_user_drive_id_seq OWNER TO postgres;

--
-- Name: __chart__per_user_drive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__per_user_drive_id_seq OWNED BY public.__chart__per_user_drive.id;


--
-- Name: __chart__per_user_following; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__per_user_following (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_followings_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_followings_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followings_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followers_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_dec smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__per_user_following OWNER TO postgres;

--
-- Name: __chart__per_user_following_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__per_user_following_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__per_user_following_id_seq OWNER TO postgres;

--
-- Name: __chart__per_user_following_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__per_user_following_id_seq OWNED BY public.__chart__per_user_following.id;


--
-- Name: __chart__per_user_notes; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__per_user_notes (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___total integer DEFAULT '0'::bigint NOT NULL,
    ___inc smallint DEFAULT '0'::bigint NOT NULL,
    ___dec smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_normal smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_reply smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_renote smallint DEFAULT '0'::bigint NOT NULL,
    "___diffs_withFile" smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart__per_user_notes OWNER TO postgres;

--
-- Name: __chart__per_user_notes_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__per_user_notes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__per_user_notes_id_seq OWNER TO postgres;

--
-- Name: __chart__per_user_notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__per_user_notes_id_seq OWNED BY public.__chart__per_user_notes.id;


--
-- Name: __chart__per_user_reaction; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__per_user_reaction (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_count smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_count smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__per_user_reaction OWNER TO postgres;

--
-- Name: __chart__per_user_reaction_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__per_user_reaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__per_user_reaction_id_seq OWNER TO postgres;

--
-- Name: __chart__per_user_reaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__per_user_reaction_id_seq OWNED BY public.__chart__per_user_reaction.id;


--
-- Name: __chart__test; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__test (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128),
    ___foo_total bigint NOT NULL,
    ___foo_inc bigint NOT NULL,
    ___foo_dec bigint NOT NULL
);


ALTER TABLE public.__chart__test OWNER TO postgres;

--
-- Name: __chart__test_grouped; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__test_grouped (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128),
    ___foo_total bigint NOT NULL,
    ___foo_inc bigint NOT NULL,
    ___foo_dec bigint NOT NULL
);


ALTER TABLE public.__chart__test_grouped OWNER TO postgres;

--
-- Name: __chart__test_grouped_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__test_grouped_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__test_grouped_id_seq OWNER TO postgres;

--
-- Name: __chart__test_grouped_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__test_grouped_id_seq OWNED BY public.__chart__test_grouped.id;


--
-- Name: __chart__test_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__test_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__test_id_seq OWNER TO postgres;

--
-- Name: __chart__test_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__test_id_seq OWNED BY public.__chart__test.id;


--
-- Name: __chart__test_unique; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__test_unique (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128),
    ___foo character varying[] DEFAULT '{}'::character varying[] NOT NULL
);


ALTER TABLE public.__chart__test_unique OWNER TO postgres;

--
-- Name: __chart__test_unique_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__test_unique_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__test_unique_id_seq OWNER TO postgres;

--
-- Name: __chart__test_unique_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__test_unique_id_seq OWNED BY public.__chart__test_unique.id;


--
-- Name: __chart__users; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart__users (
    id integer NOT NULL,
    date integer NOT NULL,
    ___local_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_dec smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart__users OWNER TO postgres;

--
-- Name: __chart__users_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart__users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart__users_id_seq OWNER TO postgres;

--
-- Name: __chart__users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart__users_id_seq OWNED BY public.__chart__users.id;


--
-- Name: __chart_day__active_users; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__active_users (
    id integer NOT NULL,
    date integer NOT NULL,
    "unique_temp___registeredWithinWeek" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinWeek" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredWithinMonth" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinMonth" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredWithinYear" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredWithinYear" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideWeek" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideWeek" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideMonth" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideMonth" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___registeredOutsideYear" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___registeredOutsideYear" smallint DEFAULT '0'::smallint NOT NULL,
    "___readWrite" smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___read character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___read smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___write character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___write smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart_day__active_users OWNER TO postgres;

--
-- Name: __chart_day__active_users_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__active_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__active_users_id_seq OWNER TO postgres;

--
-- Name: __chart_day__active_users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__active_users_id_seq OWNED BY public.__chart_day__active_users.id;


--
-- Name: __chart_day__ap_request; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__ap_request (
    id integer NOT NULL,
    date integer NOT NULL,
    "___deliverFailed" integer DEFAULT 0 NOT NULL,
    "___deliverSucceeded" integer DEFAULT 0 NOT NULL,
    "___inboxReceived" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart_day__ap_request OWNER TO postgres;

--
-- Name: __chart_day__ap_request_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__ap_request_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__ap_request_id_seq OWNER TO postgres;

--
-- Name: __chart_day__ap_request_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__ap_request_id_seq OWNED BY public.__chart_day__ap_request.id;


--
-- Name: __chart_day__drive; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__drive (
    id integer NOT NULL,
    date integer NOT NULL,
    "___local_incCount" integer DEFAULT '0'::bigint NOT NULL,
    "___local_incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___local_decCount" integer DEFAULT '0'::bigint NOT NULL,
    "___local_decSize" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_incCount" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_decCount" integer DEFAULT '0'::bigint NOT NULL,
    "___remote_decSize" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__drive OWNER TO postgres;

--
-- Name: __chart_day__drive_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__drive_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__drive_id_seq OWNER TO postgres;

--
-- Name: __chart_day__drive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__drive_id_seq OWNED BY public.__chart_day__drive.id;


--
-- Name: __chart_day__federation; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__federation (
    id integer NOT NULL,
    date integer NOT NULL,
    "unique_temp___deliveredInstances" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___deliveredInstances" smallint DEFAULT '0'::smallint NOT NULL,
    "unique_temp___inboxInstances" character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    "___inboxInstances" smallint DEFAULT '0'::smallint NOT NULL,
    unique_temp___stalled character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    ___stalled smallint DEFAULT '0'::smallint NOT NULL,
    ___sub smallint DEFAULT '0'::smallint NOT NULL,
    ___pub smallint DEFAULT '0'::smallint NOT NULL,
    ___pubsub smallint DEFAULT '0'::smallint NOT NULL,
    "___subActive" smallint DEFAULT '0'::smallint NOT NULL,
    "___pubActive" smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart_day__federation OWNER TO postgres;

--
-- Name: __chart_day__federation_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__federation_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__federation_id_seq OWNER TO postgres;

--
-- Name: __chart_day__federation_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__federation_id_seq OWNED BY public.__chart_day__federation.id;


--
-- Name: __chart_day__hashtag; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__hashtag (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_users integer DEFAULT 0 NOT NULL,
    ___remote_users integer DEFAULT 0 NOT NULL,
    unique_temp___local_users character varying[] DEFAULT '{}'::character varying[] NOT NULL,
    unique_temp___remote_users character varying[] DEFAULT '{}'::character varying[] NOT NULL
);


ALTER TABLE public.__chart_day__hashtag OWNER TO postgres;

--
-- Name: __chart_day__hashtag_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__hashtag_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__hashtag_id_seq OWNER TO postgres;

--
-- Name: __chart_day__hashtag_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__hashtag_id_seq OWNED BY public.__chart_day__hashtag.id;


--
-- Name: __chart_day__instance; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__instance (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___requests_failed smallint DEFAULT '0'::bigint NOT NULL,
    ___requests_succeeded smallint DEFAULT '0'::bigint NOT NULL,
    ___requests_received smallint DEFAULT '0'::bigint NOT NULL,
    ___notes_total integer DEFAULT '0'::bigint NOT NULL,
    ___notes_inc integer DEFAULT '0'::bigint NOT NULL,
    ___notes_dec integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___notes_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    ___users_total integer DEFAULT '0'::bigint NOT NULL,
    ___users_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___users_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___following_total integer DEFAULT '0'::bigint NOT NULL,
    ___following_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___following_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___followers_dec smallint DEFAULT '0'::bigint NOT NULL,
    "___drive_totalFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_incFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_incUsage" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_decFiles" integer DEFAULT '0'::bigint NOT NULL,
    "___drive_decUsage" integer DEFAULT '0'::bigint NOT NULL,
    "___notes_diffs_withFile" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart_day__instance OWNER TO postgres;

--
-- Name: __chart_day__instance_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__instance_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__instance_id_seq OWNER TO postgres;

--
-- Name: __chart_day__instance_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__instance_id_seq OWNED BY public.__chart_day__instance.id;


--
-- Name: __chart_day__network; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__network (
    id integer NOT NULL,
    date integer NOT NULL,
    "___incomingRequests" integer DEFAULT '0'::bigint NOT NULL,
    "___outgoingRequests" integer DEFAULT '0'::bigint NOT NULL,
    "___totalTime" integer DEFAULT '0'::bigint NOT NULL,
    "___incomingBytes" integer DEFAULT '0'::bigint NOT NULL,
    "___outgoingBytes" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__network OWNER TO postgres;

--
-- Name: __chart_day__network_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__network_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__network_id_seq OWNER TO postgres;

--
-- Name: __chart_day__network_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__network_id_seq OWNED BY public.__chart_day__network.id;


--
-- Name: __chart_day__notes; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__notes (
    id integer NOT NULL,
    date integer NOT NULL,
    ___local_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_inc integer DEFAULT '0'::bigint NOT NULL,
    ___local_dec integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___local_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    ___remote_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_inc integer DEFAULT '0'::bigint NOT NULL,
    ___remote_dec integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_normal integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_reply integer DEFAULT '0'::bigint NOT NULL,
    ___remote_diffs_renote integer DEFAULT '0'::bigint NOT NULL,
    "___local_diffs_withFile" integer DEFAULT 0 NOT NULL,
    "___remote_diffs_withFile" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.__chart_day__notes OWNER TO postgres;

--
-- Name: __chart_day__notes_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__notes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__notes_id_seq OWNER TO postgres;

--
-- Name: __chart_day__notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__notes_id_seq OWNED BY public.__chart_day__notes.id;


--
-- Name: __chart_day__per_user_drive; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__per_user_drive (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    "___totalCount" integer DEFAULT '0'::bigint NOT NULL,
    "___totalSize" integer DEFAULT '0'::bigint NOT NULL,
    "___incCount" smallint DEFAULT '0'::bigint NOT NULL,
    "___incSize" integer DEFAULT '0'::bigint NOT NULL,
    "___decCount" smallint DEFAULT '0'::bigint NOT NULL,
    "___decSize" integer DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__per_user_drive OWNER TO postgres;

--
-- Name: __chart_day__per_user_drive_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__per_user_drive_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__per_user_drive_id_seq OWNER TO postgres;

--
-- Name: __chart_day__per_user_drive_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__per_user_drive_id_seq OWNED BY public.__chart_day__per_user_drive.id;


--
-- Name: __chart_day__per_user_following; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__per_user_following (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_followings_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_followings_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followings_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_followers_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followings_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_followers_dec smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__per_user_following OWNER TO postgres;

--
-- Name: __chart_day__per_user_following_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__per_user_following_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__per_user_following_id_seq OWNER TO postgres;

--
-- Name: __chart_day__per_user_following_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__per_user_following_id_seq OWNED BY public.__chart_day__per_user_following.id;


--
-- Name: __chart_day__per_user_notes; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__per_user_notes (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___total integer DEFAULT '0'::bigint NOT NULL,
    ___inc smallint DEFAULT '0'::bigint NOT NULL,
    ___dec smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_normal smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_reply smallint DEFAULT '0'::bigint NOT NULL,
    ___diffs_renote smallint DEFAULT '0'::bigint NOT NULL,
    "___diffs_withFile" smallint DEFAULT '0'::smallint NOT NULL
);


ALTER TABLE public.__chart_day__per_user_notes OWNER TO postgres;

--
-- Name: __chart_day__per_user_notes_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__per_user_notes_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__per_user_notes_id_seq OWNER TO postgres;

--
-- Name: __chart_day__per_user_notes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__per_user_notes_id_seq OWNED BY public.__chart_day__per_user_notes.id;


--
-- Name: __chart_day__per_user_reaction; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__per_user_reaction (
    id integer NOT NULL,
    date integer NOT NULL,
    "group" character varying(128) NOT NULL,
    ___local_count smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_count smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__per_user_reaction OWNER TO postgres;

--
-- Name: __chart_day__per_user_reaction_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__per_user_reaction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__per_user_reaction_id_seq OWNER TO postgres;

--
-- Name: __chart_day__per_user_reaction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__per_user_reaction_id_seq OWNED BY public.__chart_day__per_user_reaction.id;


--
-- Name: __chart_day__users; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.__chart_day__users (
    id integer NOT NULL,
    date integer NOT NULL,
    ___local_total integer DEFAULT '0'::bigint NOT NULL,
    ___local_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___local_dec smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_total integer DEFAULT '0'::bigint NOT NULL,
    ___remote_inc smallint DEFAULT '0'::bigint NOT NULL,
    ___remote_dec smallint DEFAULT '0'::bigint NOT NULL
);


ALTER TABLE public.__chart_day__users OWNER TO postgres;

--
-- Name: __chart_day__users_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.__chart_day__users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.__chart_day__users_id_seq OWNER TO postgres;

--
-- Name: __chart_day__users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.__chart_day__users_id_seq OWNED BY public.__chart_day__users.id;


--
-- Name: abuse_user_report; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.abuse_user_report (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "targetUserId" character varying(32) NOT NULL,
    "reporterId" character varying(32) NOT NULL,
    "assigneeId" character varying(32),
    resolved boolean DEFAULT false NOT NULL,
    comment character varying(2048) NOT NULL,
    "targetUserHost" character varying(128),
    "reporterHost" character varying(128),
    forwarded boolean DEFAULT false NOT NULL
);


ALTER TABLE public.abuse_user_report OWNER TO postgres;

--
-- Name: COLUMN abuse_user_report."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.abuse_user_report."createdAt" IS 'The created date of the AbuseUserReport.';


--
-- Name: COLUMN abuse_user_report."targetUserHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.abuse_user_report."targetUserHost" IS '[Denormalized]';


--
-- Name: COLUMN abuse_user_report."reporterHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.abuse_user_report."reporterHost" IS '[Denormalized]';


--
-- Name: access_token; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.access_token (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    token character varying(128) NOT NULL,
    hash character varying(128) NOT NULL,
    "userId" character varying(32) NOT NULL,
    "appId" character varying(32),
    "lastUsedAt" timestamp with time zone,
    session character varying(128),
    name character varying(128),
    description character varying(512),
    "iconUrl" character varying(512),
    permission character varying(64)[] DEFAULT '{}'::character varying[] NOT NULL,
    fetched boolean DEFAULT false NOT NULL
);


ALTER TABLE public.access_token OWNER TO postgres;

--
-- Name: COLUMN access_token."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.access_token."createdAt" IS 'The created date of the AccessToken.';


--
-- Name: ad; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.ad (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "expiresAt" timestamp with time zone NOT NULL,
    place character varying(32) NOT NULL,
    priority character varying(32) NOT NULL,
    url character varying(1024) NOT NULL,
    "imageUrl" character varying(1024) NOT NULL,
    memo character varying(8192) NOT NULL,
    ratio integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.ad OWNER TO postgres;

--
-- Name: COLUMN ad."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.ad."createdAt" IS 'The created date of the Ad.';


--
-- Name: COLUMN ad."expiresAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.ad."expiresAt" IS 'The expired date of the Ad.';


--
-- Name: announcement; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.announcement (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    text character varying(8192) NOT NULL,
    title character varying(256) NOT NULL,
    "imageUrl" character varying(1024),
    "updatedAt" timestamp with time zone,
    "showPopup" boolean DEFAULT false NOT NULL,
    "isGoodNews" boolean DEFAULT false NOT NULL
);


ALTER TABLE public.announcement OWNER TO postgres;

--
-- Name: COLUMN announcement."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.announcement."createdAt" IS 'The created date of the Announcement.';


--
-- Name: COLUMN announcement."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.announcement."updatedAt" IS 'The updated date of the Announcement.';


--
-- Name: announcement_read; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.announcement_read (
    id character varying(32) NOT NULL,
    "userId" character varying(32) NOT NULL,
    "announcementId" character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL
);


ALTER TABLE public.announcement_read OWNER TO postgres;

--
-- Name: COLUMN announcement_read."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.announcement_read."createdAt" IS 'The created date of the AnnouncementRead.';


--
-- Name: antenna; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.antenna (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    name character varying(128) NOT NULL,
    src public.antenna_src_enum NOT NULL,
    "userListId" character varying(32),
    keywords jsonb DEFAULT '[]'::jsonb NOT NULL,
    "withFile" boolean NOT NULL,
    expression character varying(2048),
    notify boolean NOT NULL,
    "caseSensitive" boolean DEFAULT false NOT NULL,
    "withReplies" boolean DEFAULT false NOT NULL,
    "userGroupJoiningId" character varying(32),
    users character varying(1024)[] DEFAULT '{}'::character varying[] NOT NULL,
    "excludeKeywords" jsonb DEFAULT '[]'::jsonb NOT NULL,
    instances jsonb DEFAULT '[]'::jsonb NOT NULL
);


ALTER TABLE public.antenna OWNER TO postgres;

--
-- Name: COLUMN antenna."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.antenna."createdAt" IS 'The created date of the Antenna.';


--
-- Name: COLUMN antenna."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.antenna."userId" IS 'The owner ID.';


--
-- Name: COLUMN antenna.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.antenna.name IS 'The name of the Antenna.';


--
-- Name: app; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.app (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32),
    secret character varying(64) NOT NULL,
    name character varying(128) NOT NULL,
    description character varying(512) NOT NULL,
    permission character varying(64)[] NOT NULL,
    "callbackUrl" character varying(512)
);


ALTER TABLE public.app OWNER TO postgres;

--
-- Name: COLUMN app."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app."createdAt" IS 'The created date of the App.';


--
-- Name: COLUMN app."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app."userId" IS 'The owner ID.';


--
-- Name: COLUMN app.secret; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app.secret IS 'The secret key of the App.';


--
-- Name: COLUMN app.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app.name IS 'The name of the App.';


--
-- Name: COLUMN app.description; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app.description IS 'The description of the App.';


--
-- Name: COLUMN app.permission; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app.permission IS 'The permission of the App.';


--
-- Name: COLUMN app."callbackUrl"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.app."callbackUrl" IS 'The callbackUrl of the App.';


--
-- Name: attestation_challenge; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.attestation_challenge (
    id character varying(32) NOT NULL,
    "userId" character varying(32) NOT NULL,
    challenge character varying(64) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "registrationChallenge" boolean DEFAULT false NOT NULL
);


ALTER TABLE public.attestation_challenge OWNER TO postgres;

--
-- Name: COLUMN attestation_challenge.challenge; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.attestation_challenge.challenge IS 'Hex-encoded sha256 hash of the challenge.';


--
-- Name: COLUMN attestation_challenge."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.attestation_challenge."createdAt" IS 'The date challenge was created for expiry purposes.';


--
-- Name: COLUMN attestation_challenge."registrationChallenge"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.attestation_challenge."registrationChallenge" IS 'Indicates that the challenge is only for registration purposes if true to prevent the challenge for being used as authentication.';


--
-- Name: auth_session; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.auth_session (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    token character varying(128) NOT NULL,
    "userId" character varying(32),
    "appId" character varying(32) NOT NULL
);


ALTER TABLE public.auth_session OWNER TO postgres;

--
-- Name: COLUMN auth_session."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.auth_session."createdAt" IS 'The created date of the AuthSession.';


--
-- Name: blocking; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.blocking (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "blockeeId" character varying(32) NOT NULL,
    "blockerId" character varying(32) NOT NULL
);


ALTER TABLE public.blocking OWNER TO postgres;

--
-- Name: COLUMN blocking."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.blocking."createdAt" IS 'The created date of the Blocking.';


--
-- Name: COLUMN blocking."blockeeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.blocking."blockeeId" IS 'The blockee user ID.';


--
-- Name: COLUMN blocking."blockerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.blocking."blockerId" IS 'The blocker user ID.';


--
-- Name: channel; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.channel (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "lastNotedAt" timestamp with time zone,
    "userId" character varying(32),
    name character varying(128) NOT NULL,
    description character varying(2048),
    "bannerId" character varying(32),
    "notesCount" integer DEFAULT 0 NOT NULL,
    "usersCount" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.channel OWNER TO postgres;

--
-- Name: COLUMN channel."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel."createdAt" IS 'The created date of the Channel.';


--
-- Name: COLUMN channel."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel."userId" IS 'The owner ID.';


--
-- Name: COLUMN channel.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel.name IS 'The name of the Channel.';


--
-- Name: COLUMN channel.description; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel.description IS 'The description of the Channel.';


--
-- Name: COLUMN channel."bannerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel."bannerId" IS 'The ID of banner Channel.';


--
-- Name: COLUMN channel."notesCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel."notesCount" IS 'The count of notes.';


--
-- Name: COLUMN channel."usersCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel."usersCount" IS 'The count of users.';


--
-- Name: channel_following; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.channel_following (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "followeeId" character varying(32) NOT NULL,
    "followerId" character varying(32) NOT NULL
);


ALTER TABLE public.channel_following OWNER TO postgres;

--
-- Name: COLUMN channel_following."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel_following."createdAt" IS 'The created date of the ChannelFollowing.';


--
-- Name: COLUMN channel_following."followeeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel_following."followeeId" IS 'The followee channel ID.';


--
-- Name: COLUMN channel_following."followerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel_following."followerId" IS 'The follower user ID.';


--
-- Name: channel_note_pining; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.channel_note_pining (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "channelId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL
);


ALTER TABLE public.channel_note_pining OWNER TO postgres;

--
-- Name: COLUMN channel_note_pining."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.channel_note_pining."createdAt" IS 'The created date of the ChannelNotePining.';


--
-- Name: clip; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.clip (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    name character varying(128) NOT NULL,
    "isPublic" boolean DEFAULT false NOT NULL,
    description character varying(2048)
);


ALTER TABLE public.clip OWNER TO postgres;

--
-- Name: COLUMN clip."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip."createdAt" IS 'The created date of the Clip.';


--
-- Name: COLUMN clip."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip."userId" IS 'The owner ID.';


--
-- Name: COLUMN clip.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip.name IS 'The name of the Clip.';


--
-- Name: COLUMN clip.description; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip.description IS 'The description of the Clip.';


--
-- Name: clip_note; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.clip_note (
    id character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    "clipId" character varying(32) NOT NULL
);


ALTER TABLE public.clip_note OWNER TO postgres;

--
-- Name: COLUMN clip_note."noteId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip_note."noteId" IS 'The note ID.';


--
-- Name: COLUMN clip_note."clipId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.clip_note."clipId" IS 'The clip ID.';


--
-- Name: drive_file; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.drive_file (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32),
    "userHost" character varying(128),
    md5 character varying(32) NOT NULL,
    name character varying(256) NOT NULL,
    type character varying(128) NOT NULL,
    size integer NOT NULL,
    comment character varying(8192),
    properties jsonb DEFAULT '{}'::jsonb NOT NULL,
    "storedInternal" boolean NOT NULL,
    url character varying(512) NOT NULL,
    "thumbnailUrl" character varying(512),
    "webpublicUrl" character varying(512),
    "accessKey" character varying(256),
    "thumbnailAccessKey" character varying(256),
    "webpublicAccessKey" character varying(256),
    uri character varying(512),
    src character varying(512),
    "folderId" character varying(32),
    "isSensitive" boolean DEFAULT false NOT NULL,
    "isLink" boolean DEFAULT false NOT NULL,
    blurhash character varying(128),
    "webpublicType" character varying(128),
    "requestHeaders" jsonb DEFAULT '{}'::jsonb,
    "requestIp" character varying(128),
    "maybeSensitive" boolean DEFAULT false NOT NULL,
    "maybePorn" boolean DEFAULT false NOT NULL
);


ALTER TABLE public.drive_file OWNER TO postgres;

--
-- Name: COLUMN drive_file."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."createdAt" IS 'The created date of the DriveFile.';


--
-- Name: COLUMN drive_file."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."userId" IS 'The owner ID.';


--
-- Name: COLUMN drive_file."userHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."userHost" IS 'The host of owner. It will be null if the user in local.';


--
-- Name: COLUMN drive_file.md5; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.md5 IS 'The MD5 hash of the DriveFile.';


--
-- Name: COLUMN drive_file.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.name IS 'The file name of the DriveFile.';


--
-- Name: COLUMN drive_file.type; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.type IS 'The content type (MIME) of the DriveFile.';


--
-- Name: COLUMN drive_file.size; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.size IS 'The file size (bytes) of the DriveFile.';


--
-- Name: COLUMN drive_file.comment; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.comment IS 'The comment of the DriveFile.';


--
-- Name: COLUMN drive_file.properties; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.properties IS 'The any properties of the DriveFile. For example, it includes image width/height.';


--
-- Name: COLUMN drive_file.url; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.url IS 'The URL of the DriveFile.';


--
-- Name: COLUMN drive_file."thumbnailUrl"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."thumbnailUrl" IS 'The URL of the thumbnail of the DriveFile.';


--
-- Name: COLUMN drive_file."webpublicUrl"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."webpublicUrl" IS 'The URL of the webpublic of the DriveFile.';


--
-- Name: COLUMN drive_file.uri; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.uri IS 'The URI of the DriveFile. it will be null when the DriveFile is local.';


--
-- Name: COLUMN drive_file."folderId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."folderId" IS 'The parent folder ID. If null, it means the DriveFile is located in root.';


--
-- Name: COLUMN drive_file."isSensitive"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."isSensitive" IS 'Whether the DriveFile is NSFW.';


--
-- Name: COLUMN drive_file."isLink"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."isLink" IS 'Whether the DriveFile is direct link to remote server.';


--
-- Name: COLUMN drive_file.blurhash; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file.blurhash IS 'The BlurHash string.';


--
-- Name: COLUMN drive_file."maybeSensitive"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_file."maybeSensitive" IS 'Whether the DriveFile is NSFW. (predict)';


--
-- Name: drive_folder; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.drive_folder (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    name character varying(128) NOT NULL,
    "userId" character varying(32),
    "parentId" character varying(32)
);


ALTER TABLE public.drive_folder OWNER TO postgres;

--
-- Name: COLUMN drive_folder."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_folder."createdAt" IS 'The created date of the DriveFolder.';


--
-- Name: COLUMN drive_folder.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_folder.name IS 'The name of the DriveFolder.';


--
-- Name: COLUMN drive_folder."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_folder."userId" IS 'The owner ID.';


--
-- Name: COLUMN drive_folder."parentId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.drive_folder."parentId" IS 'The parent folder ID. If null, it means the DriveFolder is located in root.';


--
-- Name: emoji; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.emoji (
    id character varying(32) NOT NULL,
    "updatedAt" timestamp with time zone,
    name character varying(128) NOT NULL,
    host character varying(128),
    "originalUrl" character varying(512) NOT NULL,
    uri character varying(512),
    type character varying(64),
    aliases character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    category character varying(128),
    "publicUrl" character varying(512) DEFAULT ''::character varying NOT NULL,
    license character varying(1024),
    width integer,
    height integer
);


ALTER TABLE public.emoji OWNER TO postgres;

--
-- Name: COLUMN emoji.width; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.emoji.width IS 'Image width';


--
-- Name: COLUMN emoji.height; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.emoji.height IS 'Image height';


--
-- Name: follow_request; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.follow_request (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "followeeId" character varying(32) NOT NULL,
    "followerId" character varying(32) NOT NULL,
    "requestId" character varying(128),
    "followerHost" character varying(128),
    "followerInbox" character varying(512),
    "followerSharedInbox" character varying(512),
    "followeeHost" character varying(128),
    "followeeInbox" character varying(512),
    "followeeSharedInbox" character varying(512)
);


ALTER TABLE public.follow_request OWNER TO postgres;

--
-- Name: COLUMN follow_request."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."createdAt" IS 'The created date of the FollowRequest.';


--
-- Name: COLUMN follow_request."followeeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followeeId" IS 'The followee user ID.';


--
-- Name: COLUMN follow_request."followerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followerId" IS 'The follower user ID.';


--
-- Name: COLUMN follow_request."requestId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."requestId" IS 'id of Follow Activity.';


--
-- Name: COLUMN follow_request."followerHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followerHost" IS '[Denormalized]';


--
-- Name: COLUMN follow_request."followerInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followerInbox" IS '[Denormalized]';


--
-- Name: COLUMN follow_request."followerSharedInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followerSharedInbox" IS '[Denormalized]';


--
-- Name: COLUMN follow_request."followeeHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followeeHost" IS '[Denormalized]';


--
-- Name: COLUMN follow_request."followeeInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followeeInbox" IS '[Denormalized]';


--
-- Name: COLUMN follow_request."followeeSharedInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.follow_request."followeeSharedInbox" IS '[Denormalized]';


--
-- Name: following; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.following (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "followeeId" character varying(32) NOT NULL,
    "followerId" character varying(32) NOT NULL,
    "followerHost" character varying(128),
    "followerInbox" character varying(512),
    "followerSharedInbox" character varying(512),
    "followeeHost" character varying(128),
    "followeeInbox" character varying(512),
    "followeeSharedInbox" character varying(512)
);


ALTER TABLE public.following OWNER TO postgres;

--
-- Name: COLUMN following."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."createdAt" IS 'The created date of the Following.';


--
-- Name: COLUMN following."followeeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followeeId" IS 'The followee user ID.';


--
-- Name: COLUMN following."followerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followerId" IS 'The follower user ID.';


--
-- Name: COLUMN following."followerHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followerHost" IS '[Denormalized]';


--
-- Name: COLUMN following."followerInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followerInbox" IS '[Denormalized]';


--
-- Name: COLUMN following."followerSharedInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followerSharedInbox" IS '[Denormalized]';


--
-- Name: COLUMN following."followeeHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followeeHost" IS '[Denormalized]';


--
-- Name: COLUMN following."followeeInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followeeInbox" IS '[Denormalized]';


--
-- Name: COLUMN following."followeeSharedInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.following."followeeSharedInbox" IS '[Denormalized]';


--
-- Name: gallery_like; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.gallery_like (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "postId" character varying(32) NOT NULL
);


ALTER TABLE public.gallery_like OWNER TO postgres;

--
-- Name: gallery_post; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.gallery_post (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    title character varying(256) NOT NULL,
    description character varying(2048),
    "userId" character varying(32) NOT NULL,
    "fileIds" character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    "isSensitive" boolean DEFAULT false NOT NULL,
    "likedCount" integer DEFAULT 0 NOT NULL,
    tags character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL
);


ALTER TABLE public.gallery_post OWNER TO postgres;

--
-- Name: COLUMN gallery_post."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.gallery_post."createdAt" IS 'The created date of the GalleryPost.';


--
-- Name: COLUMN gallery_post."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.gallery_post."updatedAt" IS 'The updated date of the GalleryPost.';


--
-- Name: COLUMN gallery_post."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.gallery_post."userId" IS 'The ID of author.';


--
-- Name: COLUMN gallery_post."isSensitive"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.gallery_post."isSensitive" IS 'Whether the post is sensitive.';


--
-- Name: hashtag; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.hashtag (
    id character varying(32) NOT NULL,
    name character varying(128) NOT NULL,
    "mentionedUserIds" character varying(32)[] NOT NULL,
    "mentionedUsersCount" integer DEFAULT 0 NOT NULL,
    "mentionedLocalUserIds" character varying(32)[] NOT NULL,
    "mentionedLocalUsersCount" integer DEFAULT 0 NOT NULL,
    "mentionedRemoteUserIds" character varying(32)[] NOT NULL,
    "mentionedRemoteUsersCount" integer DEFAULT 0 NOT NULL,
    "attachedUserIds" character varying(32)[] NOT NULL,
    "attachedUsersCount" integer DEFAULT 0 NOT NULL,
    "attachedLocalUserIds" character varying(32)[] NOT NULL,
    "attachedLocalUsersCount" integer DEFAULT 0 NOT NULL,
    "attachedRemoteUserIds" character varying(32)[] NOT NULL,
    "attachedRemoteUsersCount" integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.hashtag OWNER TO postgres;

--
-- Name: instance; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.instance (
    id character varying(32) NOT NULL,
    "caughtAt" timestamp with time zone NOT NULL,
    host character varying(128) NOT NULL,
    "usersCount" integer DEFAULT 0 NOT NULL,
    "notesCount" integer DEFAULT 0 NOT NULL,
    "followingCount" integer DEFAULT 0 NOT NULL,
    "followersCount" integer DEFAULT 0 NOT NULL,
    "latestRequestSentAt" timestamp with time zone,
    "latestStatus" integer,
    "latestRequestReceivedAt" timestamp with time zone,
    "lastCommunicatedAt" timestamp with time zone NOT NULL,
    "isNotResponding" boolean DEFAULT false NOT NULL,
    "softwareName" character varying(64),
    "softwareVersion" character varying(64),
    "openRegistrations" boolean,
    name character varying(256),
    description character varying(4096),
    "maintainerName" character varying(128),
    "maintainerEmail" character varying(256),
    "infoUpdatedAt" timestamp with time zone,
    "isSuspended" boolean DEFAULT false NOT NULL,
    "iconUrl" character varying(256),
    "themeColor" character varying(64),
    "faviconUrl" character varying(256)
);


ALTER TABLE public.instance OWNER TO postgres;

--
-- Name: COLUMN instance."caughtAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.instance."caughtAt" IS 'The caught date of the Instance.';


--
-- Name: COLUMN instance.host; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.instance.host IS 'The host of the Instance.';


--
-- Name: COLUMN instance."usersCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.instance."usersCount" IS 'The count of the users of the Instance.';


--
-- Name: COLUMN instance."notesCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.instance."notesCount" IS 'The count of the notes of the Instance.';


--
-- Name: COLUMN instance."softwareName"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.instance."softwareName" IS 'The software of the Instance.';


--
-- Name: messaging_message; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.messaging_message (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "recipientId" character varying(32),
    text character varying(4096),
    "isRead" boolean DEFAULT false NOT NULL,
    "fileId" character varying(32),
    "groupId" character varying(32),
    reads character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    uri character varying(512)
);


ALTER TABLE public.messaging_message OWNER TO postgres;

--
-- Name: COLUMN messaging_message."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.messaging_message."createdAt" IS 'The created date of the MessagingMessage.';


--
-- Name: COLUMN messaging_message."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.messaging_message."userId" IS 'The sender user ID.';


--
-- Name: COLUMN messaging_message."recipientId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.messaging_message."recipientId" IS 'The recipient user ID.';


--
-- Name: COLUMN messaging_message."groupId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.messaging_message."groupId" IS 'The recipient group ID.';


--
-- Name: meta; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.meta (
    id character varying(32) NOT NULL,
    name character varying(128),
    description character varying(1024),
    "maintainerName" character varying(128),
    "maintainerEmail" character varying(128),
    "disableRegistration" boolean DEFAULT false NOT NULL,
    "disableLocalTimeline" boolean DEFAULT false NOT NULL,
    "disableGlobalTimeline" boolean DEFAULT false NOT NULL,
    "useStarForReactionFallback" boolean DEFAULT false NOT NULL,
    langs character varying(64)[] DEFAULT '{}'::character varying[] NOT NULL,
    "hiddenTags" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "blockedHosts" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "mascotImageUrl" character varying(512) DEFAULT '/static-assets/badges/info.png'::character varying,
    "bannerUrl" character varying(512),
    "errorImageUrl" character varying(512) DEFAULT '/static-assets/badges/error.png'::character varying,
    "iconUrl" character varying(512),
    "cacheRemoteFiles" boolean DEFAULT false NOT NULL,
    "enableRecaptcha" boolean DEFAULT false NOT NULL,
    "recaptchaSiteKey" character varying(64),
    "recaptchaSecretKey" character varying(64),
    "localDriveCapacityMb" integer DEFAULT 1024 NOT NULL,
    "remoteDriveCapacityMb" integer DEFAULT 32 NOT NULL,
    "summalyProxy" character varying(128),
    "enableEmail" boolean DEFAULT false NOT NULL,
    email character varying(128),
    "smtpSecure" boolean DEFAULT false NOT NULL,
    "smtpHost" character varying(128),
    "smtpPort" integer,
    "smtpUser" character varying(1024),
    "smtpPass" character varying(1024),
    "enableServiceWorker" boolean DEFAULT false NOT NULL,
    "swPublicKey" character varying(128),
    "swPrivateKey" character varying(128),
    "enableTwitterIntegration" boolean DEFAULT false NOT NULL,
    "twitterConsumerKey" character varying(128),
    "twitterConsumerSecret" character varying(128),
    "enableGithubIntegration" boolean DEFAULT false NOT NULL,
    "githubClientId" character varying(128),
    "githubClientSecret" character varying(128),
    "enableDiscordIntegration" boolean DEFAULT false NOT NULL,
    "discordClientId" character varying(128),
    "discordClientSecret" character varying(128),
    "pinnedUsers" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "ToSUrl" character varying(512),
    "repositoryUrl" character varying(512) DEFAULT 'https://codeberg.org/firefish/firefish'::character varying NOT NULL,
    "feedbackUrl" character varying(512) DEFAULT 'https://codeberg.org/firefish/firefish/issues'::character varying,
    "useObjectStorage" boolean DEFAULT false NOT NULL,
    "objectStorageBucket" character varying(512),
    "objectStoragePrefix" character varying(512),
    "objectStorageBaseUrl" character varying(512),
    "objectStorageEndpoint" character varying(512),
    "objectStorageRegion" character varying(512),
    "objectStorageAccessKey" character varying(512),
    "objectStorageSecretKey" character varying(512),
    "objectStoragePort" integer,
    "objectStorageUseSSL" boolean DEFAULT true NOT NULL,
    "proxyAccountId" character varying(32),
    "objectStorageUseProxy" boolean DEFAULT true NOT NULL,
    "enableHcaptcha" boolean DEFAULT false NOT NULL,
    "hcaptchaSiteKey" character varying(64),
    "hcaptchaSecretKey" character varying(64),
    "objectStorageSetPublicRead" boolean DEFAULT false NOT NULL,
    "pinnedPages" character varying(512)[] DEFAULT '{/featured,/channels,/explore,/pages,/about-misskey}'::character varying[] NOT NULL,
    "backgroundImageUrl" character varying(512),
    "logoImageUrl" character varying(512),
    "pinnedClipId" character varying(32),
    "objectStorageS3ForcePathStyle" boolean DEFAULT true NOT NULL,
    "allowedHosts" character varying(256)[] DEFAULT '{}'::character varying[],
    "secureMode" boolean DEFAULT false,
    "privateMode" boolean DEFAULT false,
    "deeplAuthKey" character varying(128),
    "deeplIsPro" boolean DEFAULT false NOT NULL,
    "emailRequiredForSignup" boolean DEFAULT false NOT NULL,
    "themeColor" character varying(512),
    "defaultLightTheme" character varying(8192),
    "defaultDarkTheme" character varying(8192),
    "sensitiveMediaDetection" public.meta_sensitivemediadetection_enum DEFAULT 'none'::public.meta_sensitivemediadetection_enum NOT NULL,
    "sensitiveMediaDetectionSensitivity" public.meta_sensitivemediadetectionsensitivity_enum DEFAULT 'medium'::public.meta_sensitivemediadetectionsensitivity_enum NOT NULL,
    "setSensitiveFlagAutomatically" boolean DEFAULT false NOT NULL,
    "enableIpLogging" boolean DEFAULT false NOT NULL,
    "enableSensitiveMediaDetectionForVideos" boolean DEFAULT false NOT NULL,
    "enableActiveEmailValidation" boolean DEFAULT true NOT NULL,
    "customMOTD" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "customSplashIcons" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "disableRecommendedTimeline" boolean DEFAULT true NOT NULL,
    "recommendedInstances" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "enableGuestTimeline" boolean DEFAULT false NOT NULL,
    "defaultReaction" character varying(256) DEFAULT '⭐'::character varying NOT NULL,
    "libreTranslateApiUrl" character varying(512),
    "libreTranslateApiKey" character varying(128),
    "silencedHosts" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "experimentalFeatures" jsonb DEFAULT '{}'::jsonb NOT NULL,
    "enableServerMachineStats" boolean DEFAULT false NOT NULL,
    "enableIdenticonGeneration" boolean DEFAULT true NOT NULL,
    "donationLink" character varying(256)
);


ALTER TABLE public.meta OWNER TO postgres;

--
-- Name: COLUMN meta."localDriveCapacityMb"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.meta."localDriveCapacityMb" IS 'Drive capacity of a local user (MB)';


--
-- Name: COLUMN meta."remoteDriveCapacityMb"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.meta."remoteDriveCapacityMb" IS 'Drive capacity of a remote user (MB)';


--
-- Name: COLUMN meta."defaultReaction"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.meta."defaultReaction" IS 'The fallback reaction for emoji reacts';


--
-- Name: migrations; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.migrations (
    id integer NOT NULL,
    "timestamp" bigint NOT NULL,
    name character varying NOT NULL
);


ALTER TABLE public.migrations OWNER TO postgres;

--
-- Name: migrations_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.migrations_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.migrations_id_seq OWNER TO postgres;

--
-- Name: migrations_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.migrations_id_seq OWNED BY public.migrations.id;


--
-- Name: moderation_log; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.moderation_log (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    type character varying(128) NOT NULL,
    info jsonb NOT NULL
);


ALTER TABLE public.moderation_log OWNER TO postgres;

--
-- Name: COLUMN moderation_log."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.moderation_log."createdAt" IS 'The created date of the ModerationLog.';


--
-- Name: muted_note; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.muted_note (
    id character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    "userId" character varying(32) NOT NULL,
    reason public.muted_note_reason_enum NOT NULL
);


ALTER TABLE public.muted_note OWNER TO postgres;

--
-- Name: COLUMN muted_note."noteId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muted_note."noteId" IS 'The note ID.';


--
-- Name: COLUMN muted_note."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muted_note."userId" IS 'The user ID.';


--
-- Name: COLUMN muted_note.reason; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muted_note.reason IS 'The reason of the MutedNote.';


--
-- Name: muting; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.muting (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "muteeId" character varying(32) NOT NULL,
    "muterId" character varying(32) NOT NULL,
    "expiresAt" timestamp with time zone
);


ALTER TABLE public.muting OWNER TO postgres;

--
-- Name: COLUMN muting."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muting."createdAt" IS 'The created date of the Muting.';


--
-- Name: COLUMN muting."muteeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muting."muteeId" IS 'The mutee user ID.';


--
-- Name: COLUMN muting."muterId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.muting."muterId" IS 'The muter user ID.';


--
-- Name: note; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "replyId" character varying(32),
    "renoteId" character varying(32),
    text text,
    name character varying(256),
    cw character varying(512),
    "userId" character varying(32) NOT NULL,
    "localOnly" boolean DEFAULT false NOT NULL,
    "renoteCount" smallint DEFAULT 0 NOT NULL,
    "repliesCount" smallint DEFAULT 0 NOT NULL,
    reactions jsonb DEFAULT '{}'::jsonb NOT NULL,
    visibility public.note_visibility_enum NOT NULL,
    uri character varying(512),
    score integer DEFAULT 0 NOT NULL,
    "fileIds" character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    "attachedFileTypes" character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    "visibleUserIds" character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    mentions character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    "mentionedRemoteUsers" text DEFAULT '[]'::text NOT NULL,
    emojis character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    tags character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    "hasPoll" boolean DEFAULT false NOT NULL,
    "userHost" character varying(128),
    "replyUserId" character varying(32),
    "replyUserHost" character varying(128),
    "renoteUserId" character varying(32),
    "renoteUserHost" character varying(128),
    url character varying(512),
    "channelId" character varying(32),
    "threadId" character varying(256),
    "updatedAt" timestamp with time zone
);


ALTER TABLE public.note OWNER TO postgres;

--
-- Name: COLUMN note."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."createdAt" IS 'The created date of the Note.';


--
-- Name: COLUMN note."replyId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."replyId" IS 'The ID of reply target.';


--
-- Name: COLUMN note."renoteId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."renoteId" IS 'The ID of renote target.';


--
-- Name: COLUMN note."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."userId" IS 'The ID of author.';


--
-- Name: COLUMN note.uri; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note.uri IS 'The URI of a note. it will be null when the note is local.';


--
-- Name: COLUMN note."userHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."userHost" IS '[Denormalized]';


--
-- Name: COLUMN note."replyUserId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."replyUserId" IS '[Denormalized]';


--
-- Name: COLUMN note."replyUserHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."replyUserHost" IS '[Denormalized]';


--
-- Name: COLUMN note."renoteUserId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."renoteUserId" IS '[Denormalized]';


--
-- Name: COLUMN note."renoteUserHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."renoteUserHost" IS '[Denormalized]';


--
-- Name: COLUMN note.url; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note.url IS 'The human readable url of a note. it will be null when the note is local.';


--
-- Name: COLUMN note."channelId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."channelId" IS 'The ID of source channel.';


--
-- Name: COLUMN note."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note."updatedAt" IS 'The updated date of the Note.';


--
-- Name: note_edit; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_edit (
    id character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    text text,
    cw character varying(512),
    "fileIds" character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL
);


ALTER TABLE public.note_edit OWNER TO postgres;

--
-- Name: COLUMN note_edit."noteId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_edit."noteId" IS 'The ID of note.';


--
-- Name: COLUMN note_edit."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_edit."updatedAt" IS 'The updated date of the Note.';


--
-- Name: note_favorite; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_favorite (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL
);


ALTER TABLE public.note_favorite OWNER TO postgres;

--
-- Name: COLUMN note_favorite."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_favorite."createdAt" IS 'The created date of the NoteFavorite.';


--
-- Name: note_reaction; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_reaction (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    reaction character varying(260) NOT NULL
);


ALTER TABLE public.note_reaction OWNER TO postgres;

--
-- Name: COLUMN note_reaction."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_reaction."createdAt" IS 'The created date of the NoteReaction.';


--
-- Name: note_thread_muting; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_thread_muting (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "threadId" character varying(256) NOT NULL
);


ALTER TABLE public.note_thread_muting OWNER TO postgres;

--
-- Name: note_unread; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_unread (
    id character varying(32) NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    "noteUserId" character varying(32) NOT NULL,
    "isSpecified" boolean NOT NULL,
    "isMentioned" boolean NOT NULL,
    "noteChannelId" character varying(32)
);


ALTER TABLE public.note_unread OWNER TO postgres;

--
-- Name: COLUMN note_unread."noteUserId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_unread."noteUserId" IS '[Denormalized]';


--
-- Name: COLUMN note_unread."noteChannelId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_unread."noteChannelId" IS '[Denormalized]';


--
-- Name: note_watching; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.note_watching (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    "noteUserId" character varying(32) NOT NULL
);


ALTER TABLE public.note_watching OWNER TO postgres;

--
-- Name: COLUMN note_watching."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_watching."createdAt" IS 'The created date of the NoteWatching.';


--
-- Name: COLUMN note_watching."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_watching."userId" IS 'The watcher ID.';


--
-- Name: COLUMN note_watching."noteId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_watching."noteId" IS 'The target Note ID.';


--
-- Name: COLUMN note_watching."noteUserId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.note_watching."noteUserId" IS '[Denormalized]';


--
-- Name: notification; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.notification (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "notifieeId" character varying(32) NOT NULL,
    "notifierId" character varying(32),
    "isRead" boolean DEFAULT false NOT NULL,
    "noteId" character varying(32),
    reaction character varying(128),
    choice integer,
    "followRequestId" character varying(32),
    type public.notification_type_enum NOT NULL,
    "userGroupInvitationId" character varying(32),
    "customBody" character varying(2048),
    "customHeader" character varying(256),
    "customIcon" character varying(1024),
    "appAccessTokenId" character varying(32)
);


ALTER TABLE public.notification OWNER TO postgres;

--
-- Name: COLUMN notification."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.notification."createdAt" IS 'The created date of the Notification.';


--
-- Name: COLUMN notification."notifieeId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.notification."notifieeId" IS 'The ID of recipient user of the Notification.';


--
-- Name: COLUMN notification."notifierId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.notification."notifierId" IS 'The ID of sender user of the Notification.';


--
-- Name: COLUMN notification."isRead"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.notification."isRead" IS 'Whether the Notification is read.';


--
-- Name: COLUMN notification.type; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.notification.type IS 'The type of the Notification.';


--
-- Name: page; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.page (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    title character varying(256) NOT NULL,
    name character varying(256) NOT NULL,
    summary character varying(256),
    "alignCenter" boolean NOT NULL,
    font character varying(32) NOT NULL,
    "userId" character varying(32) NOT NULL,
    "eyeCatchingImageId" character varying(32),
    content jsonb DEFAULT '[]'::jsonb NOT NULL,
    variables jsonb DEFAULT '[]'::jsonb NOT NULL,
    visibility public.page_visibility_enum NOT NULL,
    "visibleUserIds" character varying(32)[] DEFAULT '{}'::character varying[] NOT NULL,
    "likedCount" integer DEFAULT 0 NOT NULL,
    "hideTitleWhenPinned" boolean DEFAULT false NOT NULL,
    script character varying(16384) DEFAULT ''::character varying NOT NULL,
    "isPublic" boolean DEFAULT true NOT NULL
);


ALTER TABLE public.page OWNER TO postgres;

--
-- Name: COLUMN page."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.page."createdAt" IS 'The created date of the Page.';


--
-- Name: COLUMN page."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.page."updatedAt" IS 'The updated date of the Page.';


--
-- Name: COLUMN page."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.page."userId" IS 'The ID of author.';


--
-- Name: page_like; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.page_like (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "pageId" character varying(32) NOT NULL
);


ALTER TABLE public.page_like OWNER TO postgres;

--
-- Name: password_reset_request; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.password_reset_request (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    token character varying(256) NOT NULL,
    "userId" character varying(32) NOT NULL
);


ALTER TABLE public.password_reset_request OWNER TO postgres;

--
-- Name: poll; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.poll (
    "noteId" character varying(32) NOT NULL,
    "expiresAt" timestamp with time zone,
    multiple boolean NOT NULL,
    choices character varying(256)[] DEFAULT '{}'::character varying[] NOT NULL,
    votes integer[] NOT NULL,
    "noteVisibility" public.poll_notevisibility_enum NOT NULL,
    "userId" character varying(32) NOT NULL,
    "userHost" character varying(128)
);


ALTER TABLE public.poll OWNER TO postgres;

--
-- Name: COLUMN poll."noteVisibility"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.poll."noteVisibility" IS '[Denormalized]';


--
-- Name: COLUMN poll."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.poll."userId" IS '[Denormalized]';


--
-- Name: COLUMN poll."userHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.poll."userHost" IS '[Denormalized]';


--
-- Name: poll_vote; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.poll_vote (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL,
    choice integer NOT NULL
);


ALTER TABLE public.poll_vote OWNER TO postgres;

--
-- Name: COLUMN poll_vote."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.poll_vote."createdAt" IS 'The created date of the PollVote.';


--
-- Name: promo_note; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.promo_note (
    "noteId" character varying(32) NOT NULL,
    "expiresAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL
);


ALTER TABLE public.promo_note OWNER TO postgres;

--
-- Name: COLUMN promo_note."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.promo_note."userId" IS '[Denormalized]';


--
-- Name: promo_read; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.promo_read (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL
);


ALTER TABLE public.promo_read OWNER TO postgres;

--
-- Name: COLUMN promo_read."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.promo_read."createdAt" IS 'The created date of the PromoRead.';


--
-- Name: registration_ticket; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.registration_ticket (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    code character varying(64) NOT NULL
);


ALTER TABLE public.registration_ticket OWNER TO postgres;

--
-- Name: registry_item; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.registry_item (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    key character varying(1024) NOT NULL,
    scope character varying(1024)[] DEFAULT '{}'::character varying[] NOT NULL,
    domain character varying(512),
    value jsonb DEFAULT '{}'::jsonb
);


ALTER TABLE public.registry_item OWNER TO postgres;

--
-- Name: COLUMN registry_item."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.registry_item."createdAt" IS 'The created date of the RegistryItem.';


--
-- Name: COLUMN registry_item."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.registry_item."updatedAt" IS 'The updated date of the RegistryItem.';


--
-- Name: COLUMN registry_item."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.registry_item."userId" IS 'The owner ID.';


--
-- Name: COLUMN registry_item.key; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.registry_item.key IS 'The key of the RegistryItem.';


--
-- Name: COLUMN registry_item.value; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.registry_item.value IS 'The value of the RegistryItem.';


--
-- Name: relay; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.relay (
    id character varying(32) NOT NULL,
    inbox character varying(512) NOT NULL,
    status public.relay_status_enum NOT NULL
);


ALTER TABLE public.relay OWNER TO postgres;

--
-- Name: renote_muting; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.renote_muting (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "muteeId" character varying(32) NOT NULL,
    "muterId" character varying(32) NOT NULL
);


ALTER TABLE public.renote_muting OWNER TO postgres;

--
-- Name: seaql_migrations; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.seaql_migrations (
    version character varying NOT NULL,
    applied_at bigint NOT NULL
);


ALTER TABLE public.seaql_migrations OWNER TO postgres;

--
-- Name: signin; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.signin (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    ip character varying(128) NOT NULL,
    headers jsonb NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.signin OWNER TO postgres;

--
-- Name: COLUMN signin."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.signin."createdAt" IS 'The created date of the Signin.';


--
-- Name: sw_subscription; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.sw_subscription (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    endpoint character varying(512) NOT NULL,
    auth character varying(256) NOT NULL,
    publickey character varying(128) NOT NULL,
    "sendReadMessage" boolean DEFAULT false NOT NULL
);


ALTER TABLE public.sw_subscription OWNER TO postgres;

--
-- Name: used_username; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.used_username (
    username character varying(128) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL
);


ALTER TABLE public.used_username OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public."user" (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone,
    "lastFetchedAt" timestamp with time zone,
    username character varying(128) NOT NULL,
    "usernameLower" character varying(128) NOT NULL,
    name character varying(128),
    "followersCount" integer DEFAULT 0 NOT NULL,
    "followingCount" integer DEFAULT 0 NOT NULL,
    "notesCount" integer DEFAULT 0 NOT NULL,
    "avatarId" character varying(32),
    "bannerId" character varying(32),
    tags character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    "isSuspended" boolean DEFAULT false NOT NULL,
    "isSilenced" boolean DEFAULT false NOT NULL,
    "isLocked" boolean DEFAULT false NOT NULL,
    "isBot" boolean DEFAULT false NOT NULL,
    "isCat" boolean DEFAULT false NOT NULL,
    "isAdmin" boolean DEFAULT false NOT NULL,
    "isModerator" boolean DEFAULT false NOT NULL,
    emojis character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    host character varying(128),
    inbox character varying(512),
    "sharedInbox" character varying(512),
    featured character varying(512),
    uri character varying(512),
    token character(16),
    "isExplorable" boolean DEFAULT true NOT NULL,
    "followersUri" character varying(512),
    "lastActiveDate" timestamp with time zone,
    "hideOnlineStatus" boolean DEFAULT false NOT NULL,
    "isDeleted" boolean DEFAULT false NOT NULL,
    "driveCapacityOverrideMb" integer,
    "movedToUri" character varying(512),
    "alsoKnownAs" text,
    "speakAsCat" boolean DEFAULT true NOT NULL
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: COLUMN "user"."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."createdAt" IS 'The created date of the User.';


--
-- Name: COLUMN "user"."updatedAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."updatedAt" IS 'The updated date of the User.';


--
-- Name: COLUMN "user".username; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".username IS 'The username of the User.';


--
-- Name: COLUMN "user"."usernameLower"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."usernameLower" IS 'The username (lowercased) of the User.';


--
-- Name: COLUMN "user".name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".name IS 'The name of the User.';


--
-- Name: COLUMN "user"."followersCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."followersCount" IS 'The count of followers.';


--
-- Name: COLUMN "user"."followingCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."followingCount" IS 'The count of following.';


--
-- Name: COLUMN "user"."notesCount"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."notesCount" IS 'The count of notes.';


--
-- Name: COLUMN "user"."avatarId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."avatarId" IS 'The ID of avatar DriveFile.';


--
-- Name: COLUMN "user"."bannerId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."bannerId" IS 'The ID of banner DriveFile.';


--
-- Name: COLUMN "user"."isSuspended"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isSuspended" IS 'Whether the User is suspended.';


--
-- Name: COLUMN "user"."isSilenced"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isSilenced" IS 'Whether the User is silenced.';


--
-- Name: COLUMN "user"."isLocked"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isLocked" IS 'Whether the User is locked.';


--
-- Name: COLUMN "user"."isBot"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isBot" IS 'Whether the User is a bot.';


--
-- Name: COLUMN "user"."isCat"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isCat" IS 'Whether the User is a cat.';


--
-- Name: COLUMN "user"."isAdmin"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isAdmin" IS 'Whether the User is the admin.';


--
-- Name: COLUMN "user"."isModerator"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isModerator" IS 'Whether the User is a moderator.';


--
-- Name: COLUMN "user".host; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".host IS 'The host of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user".inbox; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".inbox IS 'The inbox URL of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user"."sharedInbox"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."sharedInbox" IS 'The sharedInbox URL of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user".featured; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".featured IS 'The featured URL of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user".uri; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".uri IS 'The URI of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user".token; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user".token IS 'The native access token of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user"."isExplorable"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isExplorable" IS 'Whether the User is explorable.';


--
-- Name: COLUMN "user"."followersUri"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."followersUri" IS 'The URI of the user Follower Collection. It will be null if the origin of the user is local.';


--
-- Name: COLUMN "user"."isDeleted"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."isDeleted" IS 'Whether the User is deleted.';


--
-- Name: COLUMN "user"."driveCapacityOverrideMb"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."driveCapacityOverrideMb" IS 'Overrides user drive capacity limit';


--
-- Name: COLUMN "user"."movedToUri"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."movedToUri" IS 'The URI of the new account of the User';


--
-- Name: COLUMN "user"."alsoKnownAs"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."alsoKnownAs" IS 'URIs the user is known as too';


--
-- Name: COLUMN "user"."speakAsCat"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public."user"."speakAsCat" IS 'Whether to speak as a cat if isCat.';


--
-- Name: user_group; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_group (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    name character varying(256) NOT NULL,
    "userId" character varying(32) NOT NULL,
    "isPrivate" boolean DEFAULT false NOT NULL
);


ALTER TABLE public.user_group OWNER TO postgres;

--
-- Name: COLUMN user_group."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group."createdAt" IS 'The created date of the UserGroup.';


--
-- Name: COLUMN user_group."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group."userId" IS 'The ID of owner.';


--
-- Name: user_group_invitation; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_group_invitation (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "userGroupId" character varying(32) NOT NULL
);


ALTER TABLE public.user_group_invitation OWNER TO postgres;

--
-- Name: COLUMN user_group_invitation."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_invitation."createdAt" IS 'The created date of the UserGroupInvitation.';


--
-- Name: COLUMN user_group_invitation."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_invitation."userId" IS 'The user ID.';


--
-- Name: COLUMN user_group_invitation."userGroupId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_invitation."userGroupId" IS 'The group ID.';


--
-- Name: user_group_invite; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_group_invite (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "userGroupId" character varying(32) NOT NULL
);


ALTER TABLE public.user_group_invite OWNER TO postgres;

--
-- Name: user_group_joining; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_group_joining (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "userGroupId" character varying(32) NOT NULL
);


ALTER TABLE public.user_group_joining OWNER TO postgres;

--
-- Name: COLUMN user_group_joining."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_joining."createdAt" IS 'The created date of the UserGroupJoining.';


--
-- Name: COLUMN user_group_joining."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_joining."userId" IS 'The user ID.';


--
-- Name: COLUMN user_group_joining."userGroupId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_group_joining."userGroupId" IS 'The group ID.';


--
-- Name: user_ip; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_ip (
    id integer NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    ip character varying(128) NOT NULL
);


ALTER TABLE public.user_ip OWNER TO postgres;

--
-- Name: user_ip_id_seq; Type: SEQUENCE; Schema: public; Owner: calckey
--

CREATE SEQUENCE public.user_ip_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_ip_id_seq OWNER TO postgres;

--
-- Name: user_ip_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: calckey
--

ALTER SEQUENCE public.user_ip_id_seq OWNED BY public.user_ip.id;


--
-- Name: user_keypair; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_keypair (
    "userId" character varying(32) NOT NULL,
    "publicKey" character varying(4096) NOT NULL,
    "privateKey" character varying(4096) NOT NULL
);


ALTER TABLE public.user_keypair OWNER TO postgres;

--
-- Name: user_list; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_list (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    name character varying(128) NOT NULL
);


ALTER TABLE public.user_list OWNER TO postgres;

--
-- Name: COLUMN user_list."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list."createdAt" IS 'The created date of the UserList.';


--
-- Name: COLUMN user_list."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list."userId" IS 'The owner ID.';


--
-- Name: COLUMN user_list.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list.name IS 'The name of the UserList.';


--
-- Name: user_list_joining; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_list_joining (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "userListId" character varying(32) NOT NULL
);


ALTER TABLE public.user_list_joining OWNER TO postgres;

--
-- Name: COLUMN user_list_joining."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list_joining."createdAt" IS 'The created date of the UserListJoining.';


--
-- Name: COLUMN user_list_joining."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list_joining."userId" IS 'The user ID.';


--
-- Name: COLUMN user_list_joining."userListId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_list_joining."userListId" IS 'The list ID.';


--
-- Name: user_note_pining; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_note_pining (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    "noteId" character varying(32) NOT NULL
);


ALTER TABLE public.user_note_pining OWNER TO postgres;

--
-- Name: COLUMN user_note_pining."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_note_pining."createdAt" IS 'The created date of the UserNotePinings.';


--
-- Name: user_pending; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_pending (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    code character varying(128) NOT NULL,
    username character varying(128) NOT NULL,
    email character varying(128) NOT NULL,
    password character varying(128) NOT NULL
);


ALTER TABLE public.user_pending OWNER TO postgres;

--
-- Name: user_profile; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_profile (
    "userId" character varying(32) NOT NULL,
    location character varying(128),
    birthday character(10),
    description character varying(2048),
    fields jsonb DEFAULT '[]'::jsonb NOT NULL,
    url character varying(512),
    email character varying(128),
    "emailVerifyCode" character varying(128),
    "emailVerified" boolean DEFAULT false NOT NULL,
    "twoFactorTempSecret" character varying(128),
    "twoFactorSecret" character varying(128),
    "twoFactorEnabled" boolean DEFAULT false NOT NULL,
    password character varying(128),
    "clientData" jsonb DEFAULT '{}'::jsonb NOT NULL,
    "autoAcceptFollowed" boolean DEFAULT false NOT NULL,
    "alwaysMarkNsfw" boolean DEFAULT false NOT NULL,
    "carefulBot" boolean DEFAULT false NOT NULL,
    "userHost" character varying(128),
    "securityKeysAvailable" boolean DEFAULT false NOT NULL,
    "usePasswordLessLogin" boolean DEFAULT false NOT NULL,
    "pinnedPageId" character varying(32),
    room jsonb DEFAULT '{}'::jsonb NOT NULL,
    integrations jsonb DEFAULT '{}'::jsonb NOT NULL,
    "injectFeaturedNote" boolean DEFAULT true NOT NULL,
    "enableWordMute" boolean DEFAULT false NOT NULL,
    "mutedWords" jsonb DEFAULT '[]'::jsonb NOT NULL,
    "mutingNotificationTypes" public.user_profile_mutingnotificationtypes_enum[] DEFAULT '{}'::public.user_profile_mutingnotificationtypes_enum[] NOT NULL,
    "noCrawle" boolean DEFAULT false NOT NULL,
    "receiveAnnouncementEmail" boolean DEFAULT true NOT NULL,
    "emailNotificationTypes" jsonb DEFAULT '["follow", "receiveFollowRequest", "groupInvited"]'::jsonb NOT NULL,
    lang character varying(32),
    "mutedInstances" jsonb DEFAULT '[]'::jsonb NOT NULL,
    "publicReactions" boolean DEFAULT false NOT NULL,
    "ffVisibility" public.user_profile_ffvisibility_enum DEFAULT 'public'::public.user_profile_ffvisibility_enum NOT NULL,
    "autoSensitive" boolean DEFAULT false NOT NULL,
    "moderationNote" character varying(8192) DEFAULT ''::character varying NOT NULL,
    "preventAiLearning" boolean DEFAULT true NOT NULL
);


ALTER TABLE public.user_profile OWNER TO postgres;

--
-- Name: COLUMN user_profile.location; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.location IS 'The location of the User.';


--
-- Name: COLUMN user_profile.birthday; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.birthday IS 'The birthday (YYYY-MM-DD) of the User.';


--
-- Name: COLUMN user_profile.description; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.description IS 'The description (bio) of the User.';


--
-- Name: COLUMN user_profile.url; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.url IS 'Remote URL of the user.';


--
-- Name: COLUMN user_profile.email; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.email IS 'The email address of the User.';


--
-- Name: COLUMN user_profile.password; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.password IS 'The password hash of the User. It will be null if the origin of the user is local.';


--
-- Name: COLUMN user_profile."clientData"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile."clientData" IS 'The client-specific data of the User.';


--
-- Name: COLUMN user_profile."userHost"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile."userHost" IS '[Denormalized]';


--
-- Name: COLUMN user_profile.room; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile.room IS 'The room data of the User.';


--
-- Name: COLUMN user_profile."noCrawle"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile."noCrawle" IS 'Whether reject index by crawler.';


--
-- Name: COLUMN user_profile."mutedInstances"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_profile."mutedInstances" IS 'List of instances muted by the user.';


--
-- Name: user_publickey; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_publickey (
    "userId" character varying(32) NOT NULL,
    "keyId" character varying(256) NOT NULL,
    "keyPem" character varying(4096) NOT NULL
);


ALTER TABLE public.user_publickey OWNER TO postgres;

--
-- Name: user_security_key; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.user_security_key (
    id character varying NOT NULL,
    "userId" character varying(32) NOT NULL,
    "publicKey" character varying NOT NULL,
    "lastUsed" timestamp with time zone NOT NULL,
    name character varying(30) NOT NULL
);


ALTER TABLE public.user_security_key OWNER TO postgres;

--
-- Name: COLUMN user_security_key.id; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_security_key.id IS 'Variable-length id given to navigator.credentials.get()';


--
-- Name: COLUMN user_security_key."publicKey"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_security_key."publicKey" IS 'Variable-length public key used to verify attestations (hex-encoded).';


--
-- Name: COLUMN user_security_key."lastUsed"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_security_key."lastUsed" IS 'The date of the last time the UserSecurityKey was successfully validated.';


--
-- Name: COLUMN user_security_key.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.user_security_key.name IS 'User-defined name for this key';


--
-- Name: webhook; Type: TABLE; Schema: public; Owner: calckey
--

CREATE TABLE public.webhook (
    id character varying(32) NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "userId" character varying(32) NOT NULL,
    name character varying(128) NOT NULL,
    "on" character varying(128)[] DEFAULT '{}'::character varying[] NOT NULL,
    url character varying(1024) NOT NULL,
    secret character varying(1024) NOT NULL,
    active boolean DEFAULT true NOT NULL,
    "latestSentAt" timestamp with time zone,
    "latestStatus" integer
);


ALTER TABLE public.webhook OWNER TO postgres;

--
-- Name: COLUMN webhook."createdAt"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.webhook."createdAt" IS 'The created date of the Antenna.';


--
-- Name: COLUMN webhook."userId"; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.webhook."userId" IS 'The owner ID.';


--
-- Name: COLUMN webhook.name; Type: COMMENT; Schema: public; Owner: calckey
--

COMMENT ON COLUMN public.webhook.name IS 'The name of the Antenna.';


--
-- Name: __chart__active_users id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__active_users ALTER COLUMN id SET DEFAULT nextval('public.__chart__active_users_id_seq'::regclass);


--
-- Name: __chart__ap_request id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__ap_request ALTER COLUMN id SET DEFAULT nextval('public.__chart__ap_request_id_seq'::regclass);


--
-- Name: __chart__drive id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__drive ALTER COLUMN id SET DEFAULT nextval('public.__chart__drive_id_seq'::regclass);


--
-- Name: __chart__federation id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__federation ALTER COLUMN id SET DEFAULT nextval('public.__chart__federation_id_seq'::regclass);


--
-- Name: __chart__hashtag id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__hashtag ALTER COLUMN id SET DEFAULT nextval('public.__chart__hashtag_id_seq'::regclass);


--
-- Name: __chart__instance id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__instance ALTER COLUMN id SET DEFAULT nextval('public.__chart__instance_id_seq'::regclass);


--
-- Name: __chart__network id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__network ALTER COLUMN id SET DEFAULT nextval('public.__chart__network_id_seq'::regclass);


--
-- Name: __chart__notes id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__notes ALTER COLUMN id SET DEFAULT nextval('public.__chart__notes_id_seq'::regclass);


--
-- Name: __chart__per_user_drive id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_drive ALTER COLUMN id SET DEFAULT nextval('public.__chart__per_user_drive_id_seq'::regclass);


--
-- Name: __chart__per_user_following id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_following ALTER COLUMN id SET DEFAULT nextval('public.__chart__per_user_following_id_seq'::regclass);


--
-- Name: __chart__per_user_notes id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_notes ALTER COLUMN id SET DEFAULT nextval('public.__chart__per_user_notes_id_seq'::regclass);


--
-- Name: __chart__per_user_reaction id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_reaction ALTER COLUMN id SET DEFAULT nextval('public.__chart__per_user_reaction_id_seq'::regclass);


--
-- Name: __chart__test id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test ALTER COLUMN id SET DEFAULT nextval('public.__chart__test_id_seq'::regclass);


--
-- Name: __chart__test_grouped id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test_grouped ALTER COLUMN id SET DEFAULT nextval('public.__chart__test_grouped_id_seq'::regclass);


--
-- Name: __chart__test_unique id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test_unique ALTER COLUMN id SET DEFAULT nextval('public.__chart__test_unique_id_seq'::regclass);


--
-- Name: __chart__users id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__users ALTER COLUMN id SET DEFAULT nextval('public.__chart__users_id_seq'::regclass);


--
-- Name: __chart_day__active_users id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__active_users ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__active_users_id_seq'::regclass);


--
-- Name: __chart_day__ap_request id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__ap_request ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__ap_request_id_seq'::regclass);


--
-- Name: __chart_day__drive id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__drive ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__drive_id_seq'::regclass);


--
-- Name: __chart_day__federation id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__federation ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__federation_id_seq'::regclass);


--
-- Name: __chart_day__hashtag id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__hashtag ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__hashtag_id_seq'::regclass);


--
-- Name: __chart_day__instance id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__instance ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__instance_id_seq'::regclass);


--
-- Name: __chart_day__network id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__network ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__network_id_seq'::regclass);


--
-- Name: __chart_day__notes id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__notes ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__notes_id_seq'::regclass);


--
-- Name: __chart_day__per_user_drive id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_drive ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__per_user_drive_id_seq'::regclass);


--
-- Name: __chart_day__per_user_following id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_following ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__per_user_following_id_seq'::regclass);


--
-- Name: __chart_day__per_user_notes id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_notes ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__per_user_notes_id_seq'::regclass);


--
-- Name: __chart_day__per_user_reaction id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_reaction ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__per_user_reaction_id_seq'::regclass);


--
-- Name: __chart_day__users id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__users ALTER COLUMN id SET DEFAULT nextval('public.__chart_day__users_id_seq'::regclass);


--
-- Name: migrations id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.migrations ALTER COLUMN id SET DEFAULT nextval('public.migrations_id_seq'::regclass);


--
-- Name: user_ip id; Type: DEFAULT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_ip ALTER COLUMN id SET DEFAULT nextval('public.user_ip_id_seq'::regclass);


--
-- Data for Name: __chart__active_users; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__active_users (id, date, "unique_temp___registeredWithinWeek", "___registeredWithinWeek", "unique_temp___registeredWithinMonth", "___registeredWithinMonth", "unique_temp___registeredWithinYear", "___registeredWithinYear", "unique_temp___registeredOutsideWeek", "___registeredOutsideWeek", "unique_temp___registeredOutsideMonth", "___registeredOutsideMonth", "unique_temp___registeredOutsideYear", "___registeredOutsideYear", "___readWrite", unique_temp___read, ___read, unique_temp___write, ___write) FROM stdin;
1	1698418800	{}	1	{}	1	{}	1	{}	0	{}	0	{}	0	0	{}	1	{}	0
2	1698422400	{}	1	{}	1	{}	1	{}	0	{}	0	{}	0	1	{}	1	{}	1
3	1698426000	{}	0	{}	0	{}	0	{}	0	{}	0	{}	0	0	{}	0	{}	1
4	1698454800	{}	1	{}	1	{}	1	{}	0	{}	0	{}	0	1	{}	1	{}	1
\.


--
-- Data for Name: __chart__ap_request; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__ap_request (id, date, "___deliverFailed", "___deliverSucceeded", "___inboxReceived") FROM stdin;
1	1698418800	0	2	2
2	1698422400	0	2	1
3	1698426000	0	1	7
4	1698451200	0	0	1
5	1698454800	0	7	3
\.


--
-- Data for Name: __chart__drive; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__drive (id, date, "___local_incCount", "___local_incSize", "___local_decCount", "___local_decSize", "___remote_incCount", "___remote_incSize", "___remote_decCount", "___remote_decSize") FROM stdin;
1	1698454800	1	9	0	0	0	0	0	0
\.


--
-- Data for Name: __chart__federation; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__federation (id, date, "unique_temp___deliveredInstances", "___deliveredInstances", "unique_temp___inboxInstances", "___inboxInstances", unique_temp___stalled, ___stalled, ___sub, ___pub, ___pubsub, "___subActive", "___pubActive") FROM stdin;
40	1698559200	{}	0	{}	0	{}	0	1	1	1	1	1
41	1698562800	{}	0	{}	0	{}	0	1	1	1	1	1
42	1698566400	{}	0	{}	0	{}	0	1	1	1	1	1
43	1698570000	{}	0	{}	0	{}	0	1	1	1	1	1
44	1698573600	{}	0	{}	0	{}	0	1	1	1	1	1
34	1698537600	{}	0	{}	0	{}	0	1	1	1	1	1
35	1698541200	{}	0	{}	0	{}	0	1	1	1	1	1
36	1698544800	{}	0	{}	0	{}	0	1	1	1	1	1
37	1698548400	{}	0	{}	0	{}	0	1	1	1	1	1
38	1698552000	{}	0	{}	0	{}	0	1	1	1	1	1
39	1698555600	{}	0	{}	0	{}	0	1	1	1	1	1
45	1698577200	{}	0	{}	0	{}	0	1	1	1	1	1
46	1698580800	{}	0	{}	0	{}	0	1	1	1	1	1
47	1698584400	{}	0	{}	0	{}	0	1	1	1	1	1
48	1698588000	{}	0	{}	0	{}	0	1	1	1	1	1
49	1698591600	{}	0	{}	0	{}	0	1	1	1	1	1
50	1698595200	{}	0	{}	0	{}	0	1	1	1	1	1
51	1698598800	{}	0	{}	0	{}	0	1	1	1	1	1
52	1698602400	{}	0	{}	0	{}	0	1	1	1	1	1
53	1698606000	{}	0	{}	0	{}	0	1	1	1	1	1
54	1698609600	{}	0	{}	0	{}	0	1	1	1	1	1
55	1698613200	{}	0	{}	0	{}	0	1	1	1	1	1
56	1698616800	{}	0	{}	0	{}	0	1	1	1	1	1
57	1698620400	{}	0	{}	0	{}	0	1	1	1	1	1
25	1698505200	{}	0	{}	0	{}	0	1	1	1	1	1
26	1698508800	{}	0	{}	0	{}	0	1	1	1	1	1
27	1698512400	{}	0	{}	0	{}	0	1	1	1	1	1
10	1698451200	{}	0	{}	1	{}	0	1	1	1	1	1
28	1698516000	{}	0	{}	0	{}	0	1	1	1	1	1
29	1698519600	{}	0	{}	0	{}	0	1	1	1	1	1
11	1698454800	{}	1	{}	1	{}	0	1	1	1	1	1
12	1698458400	{}	0	{}	0	{}	0	1	1	1	1	1
13	1698462000	{}	0	{}	0	{}	0	1	1	1	1	1
14	1698465600	{}	0	{}	0	{}	0	1	1	1	1	1
15	1698469200	{}	0	{}	0	{}	0	1	1	1	1	1
16	1698472800	{}	0	{}	0	{}	0	1	1	1	1	1
17	1698476400	{}	0	{}	0	{}	0	1	1	1	1	1
18	1698480000	{}	0	{}	0	{}	0	1	1	1	1	1
19	1698483600	{}	0	{}	0	{}	0	1	1	1	1	1
20	1698487200	{}	0	{}	0	{}	0	1	1	1	1	1
21	1698490800	{}	0	{}	0	{}	0	1	1	1	1	1
22	1698494400	{}	0	{}	0	{}	0	1	1	1	1	1
23	1698498000	{}	0	{}	0	{}	0	1	1	1	1	1
24	1698501600	{}	0	{}	0	{}	0	1	1	1	1	1
30	1698523200	{}	0	{}	0	{}	0	1	1	1	1	1
31	1698526800	{}	0	{}	0	{}	0	1	1	1	1	1
32	1698530400	{}	0	{}	0	{}	0	1	1	1	1	1
33	1698534000	{}	0	{}	0	{}	0	1	1	1	1	1
1	1698418800	{}	1	{}	1	{}	0	1	1	1	1	1
2	1698422400	{}	1	{}	1	{}	0	1	1	1	1	1
3	1698426000	{}	1	{}	1	{}	0	1	1	1	1	1
4	1698429600	{}	0	{}	0	{}	0	1	1	1	1	1
5	1698433200	{}	0	{}	0	{}	0	1	1	1	1	1
6	1698436800	{}	0	{}	0	{}	0	1	1	1	1	1
7	1698440400	{}	0	{}	0	{}	0	1	1	1	1	1
8	1698444000	{}	0	{}	0	{}	0	1	1	1	1	1
9	1698447600	{}	0	{}	0	{}	0	1	1	1	1	1
58	1698624000	{}	0	{}	0	{}	0	1	1	1	1	1
59	1698627600	{}	0	{}	0	{}	0	1	1	1	1	1
60	1698631200	{}	0	{}	0	{}	0	1	1	1	1	1
61	1698634800	{}	0	{}	0	{}	0	1	1	1	1	1
62	1698638400	{}	0	{}	0	{}	0	1	1	1	1	1
63	1698642000	{}	0	{}	0	{}	0	1	1	1	1	1
64	1698645600	{}	0	{}	0	{}	0	1	1	1	1	1
65	1698649200	{}	0	{}	0	{}	0	1	1	1	1	1
66	1698652800	{}	0	{}	0	{}	0	1	1	1	1	1
67	1698656400	{}	0	{}	0	{}	0	1	1	1	1	1
68	1698660000	{}	0	{}	0	{}	0	1	1	1	1	1
69	1698663600	{}	0	{}	0	{}	0	1	1	1	1	1
70	1698667200	{}	0	{}	0	{}	0	1	1	1	1	1
\.


--
-- Data for Name: __chart__hashtag; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__hashtag (id, date, "group", ___local_users, ___remote_users, unique_temp___local_users, unique_temp___remote_users) FROM stdin;
\.


--
-- Data for Name: __chart__instance; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__instance (id, date, "group", ___requests_failed, ___requests_succeeded, ___requests_received, ___notes_total, ___notes_inc, ___notes_dec, ___notes_diffs_normal, ___notes_diffs_reply, ___notes_diffs_renote, ___users_total, ___users_inc, ___users_dec, ___following_total, ___following_inc, ___following_dec, ___followers_total, ___followers_inc, ___followers_dec, "___drive_totalFiles", "___drive_incFiles", "___drive_incUsage", "___drive_decFiles", "___drive_decUsage", "___notes_diffs_withFile") FROM stdin;
1	1698418800	akkoma.swampnet.club	0	2	2	0	0	0	0	0	0	1	1	0	1	1	0	1	1	0	0	0	0	0	0	0
2	1698422400	akkoma.swampnet.club	0	2	1	1	1	0	1	0	0	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0
3	1698426000	akkoma.swampnet.club	0	1	7	5	4	0	3	0	1	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0
4	1698451200	akkoma.swampnet.club	0	0	1	6	1	0	0	0	1	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0
5	1698454800	akkoma.swampnet.club	0	7	3	6	0	0	0	0	0	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0
\.


--
-- Data for Name: __chart__network; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__network (id, date, "___incomingRequests", "___outgoingRequests", "___totalTime", "___incomingBytes", "___outgoingBytes") FROM stdin;
\.


--
-- Data for Name: __chart__notes; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__notes (id, date, ___local_total, ___local_inc, ___local_dec, ___local_diffs_normal, ___local_diffs_reply, ___local_diffs_renote, ___remote_total, ___remote_inc, ___remote_dec, ___remote_diffs_normal, ___remote_diffs_reply, ___remote_diffs_renote, "___local_diffs_withFile", "___remote_diffs_withFile") FROM stdin;
1	1698422400	2	2	0	2	0	0	1	1	0	1	0	0	0	0
2	1698426000	3	1	0	1	0	0	5	4	0	3	0	1	0	0
3	1698451200	3	0	0	0	0	0	6	1	0	0	0	1	0	0
4	1698454800	5	2	0	1	1	0	6	0	0	0	0	0	0	0
5	1698537600	5	0	0	0	0	0	6	0	0	0	0	0	0	0
6	1698624000	5	0	0	0	0	0	6	0	0	0	0	0	0	0
\.


--
-- Data for Name: __chart__per_user_drive; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__per_user_drive (id, date, "group", "___totalCount", "___totalSize", "___incCount", "___incSize", "___decCount", "___decSize") FROM stdin;
1	1698454800	9lcb5iw3qm0zyytv	1	9	1	9	0	0
\.


--
-- Data for Name: __chart__per_user_following; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__per_user_following (id, date, "group", ___local_followings_total, ___local_followings_inc, ___local_followings_dec, ___local_followers_total, ___local_followers_inc, ___local_followers_dec, ___remote_followings_total, ___remote_followings_inc, ___remote_followings_dec, ___remote_followers_total, ___remote_followers_inc, ___remote_followers_dec) FROM stdin;
1	1698418800	9lcb5iw3qm0zyytv	1	1	0	1	1	0	0	0	0	0	0	0
2	1698418800	9lcc5uu4cug6mxgv	0	0	0	0	0	0	1	1	0	1	1	0
\.


--
-- Data for Name: __chart__per_user_notes; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__per_user_notes (id, date, "group", ___total, ___inc, ___dec, ___diffs_normal, ___diffs_reply, ___diffs_renote, "___diffs_withFile") FROM stdin;
1	1698422400	9lcc5uu4cug6mxgv	1	1	0	1	0	0	0
2	1698422400	9lcb5iw3qm0zyytv	2	2	0	2	0	0	0
4	1698426000	9lcb5iw3qm0zyytv	3	1	0	1	0	0	0
3	1698426000	9lcc5uu4cug6mxgv	5	4	0	3	0	1	0
5	1698451200	9lcc5uu4cug6mxgv	6	1	0	0	0	1	0
6	1698454800	9lcb5iw3qm0zyytv	5	2	0	1	1	0	0
\.


--
-- Data for Name: __chart__per_user_reaction; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__per_user_reaction (id, date, "group", ___local_count, ___remote_count) FROM stdin;
1	1698426000	9lcb5iw3qm0zyytv	0	3
2	1698454800	9lcc5uu4cug6mxgv	2	0
\.


--
-- Data for Name: __chart__test; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__test (id, date, "group", ___foo_total, ___foo_inc, ___foo_dec) FROM stdin;
\.


--
-- Data for Name: __chart__test_grouped; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__test_grouped (id, date, "group", ___foo_total, ___foo_inc, ___foo_dec) FROM stdin;
\.


--
-- Data for Name: __chart__test_unique; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__test_unique (id, date, "group", ___foo) FROM stdin;
\.


--
-- Data for Name: __chart__users; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart__users (id, date, ___local_total, ___local_inc, ___local_dec, ___remote_total, ___remote_inc, ___remote_dec) FROM stdin;
1	1698418800	1	1	0	1	1	0
2	1698451200	2	0	0	1	0	0
3	1698537600	2	0	0	1	0	0
4	1698624000	2	0	0	1	0	0
\.


--
-- Data for Name: __chart_day__active_users; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__active_users (id, date, "unique_temp___registeredWithinWeek", "___registeredWithinWeek", "unique_temp___registeredWithinMonth", "___registeredWithinMonth", "unique_temp___registeredWithinYear", "___registeredWithinYear", "unique_temp___registeredOutsideWeek", "___registeredOutsideWeek", "unique_temp___registeredOutsideMonth", "___registeredOutsideMonth", "unique_temp___registeredOutsideYear", "___registeredOutsideYear", "___readWrite", unique_temp___read, ___read, unique_temp___write, ___write) FROM stdin;
1	1698364800	{}	1	{}	1	{}	1	{}	0	{}	0	{}	0	1	{}	1	{}	1
2	1698451200	{}	1	{}	1	{}	1	{}	0	{}	0	{}	0	1	{}	1	{}	1
\.


--
-- Data for Name: __chart_day__ap_request; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__ap_request (id, date, "___deliverFailed", "___deliverSucceeded", "___inboxReceived") FROM stdin;
1	1698364800	0	5	10
2	1698451200	0	7	4
\.


--
-- Data for Name: __chart_day__drive; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__drive (id, date, "___local_incCount", "___local_incSize", "___local_decCount", "___local_decSize", "___remote_incCount", "___remote_incSize", "___remote_decCount", "___remote_decSize") FROM stdin;
1	1698451200	1	9	0	0	0	0	0	0
\.


--
-- Data for Name: __chart_day__federation; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__federation (id, date, "unique_temp___deliveredInstances", "___deliveredInstances", "unique_temp___inboxInstances", "___inboxInstances", unique_temp___stalled, ___stalled, ___sub, ___pub, ___pubsub, "___subActive", "___pubActive") FROM stdin;
1	1698364800	{}	1	{}	1	{}	0	1	1	1	1	1
3	1698537600	{}	0	{}	0	{}	0	1	1	1	1	1
2	1698451200	{}	1	{}	1	{}	0	1	1	1	1	1
4	1698624000	{}	0	{}	0	{}	0	1	1	1	1	1
\.


--
-- Data for Name: __chart_day__hashtag; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__hashtag (id, date, "group", ___local_users, ___remote_users, unique_temp___local_users, unique_temp___remote_users) FROM stdin;
\.


--
-- Data for Name: __chart_day__instance; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__instance (id, date, "group", ___requests_failed, ___requests_succeeded, ___requests_received, ___notes_total, ___notes_inc, ___notes_dec, ___notes_diffs_normal, ___notes_diffs_reply, ___notes_diffs_renote, ___users_total, ___users_inc, ___users_dec, ___following_total, ___following_inc, ___following_dec, ___followers_total, ___followers_inc, ___followers_dec, "___drive_totalFiles", "___drive_incFiles", "___drive_incUsage", "___drive_decFiles", "___drive_decUsage", "___notes_diffs_withFile") FROM stdin;
1	1698364800	akkoma.swampnet.club	0	5	10	5	5	0	4	0	1	1	1	0	1	1	0	1	1	0	0	0	0	0	0	0
2	1698451200	akkoma.swampnet.club	0	7	4	6	1	0	0	0	1	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0
\.


--
-- Data for Name: __chart_day__network; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__network (id, date, "___incomingRequests", "___outgoingRequests", "___totalTime", "___incomingBytes", "___outgoingBytes") FROM stdin;
\.


--
-- Data for Name: __chart_day__notes; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__notes (id, date, ___local_total, ___local_inc, ___local_dec, ___local_diffs_normal, ___local_diffs_reply, ___local_diffs_renote, ___remote_total, ___remote_inc, ___remote_dec, ___remote_diffs_normal, ___remote_diffs_reply, ___remote_diffs_renote, "___local_diffs_withFile", "___remote_diffs_withFile") FROM stdin;
1	1698364800	3	3	0	3	0	0	5	5	0	4	0	1	0	0
2	1698451200	5	2	0	1	1	0	6	1	0	0	0	1	0	0
3	1698537600	5	0	0	0	0	0	6	0	0	0	0	0	0	0
4	1698624000	5	0	0	0	0	0	6	0	0	0	0	0	0	0
\.


--
-- Data for Name: __chart_day__per_user_drive; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__per_user_drive (id, date, "group", "___totalCount", "___totalSize", "___incCount", "___incSize", "___decCount", "___decSize") FROM stdin;
1	1698451200	9lcb5iw3qm0zyytv	1	9	1	9	0	0
\.


--
-- Data for Name: __chart_day__per_user_following; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__per_user_following (id, date, "group", ___local_followings_total, ___local_followings_inc, ___local_followings_dec, ___local_followers_total, ___local_followers_inc, ___local_followers_dec, ___remote_followings_total, ___remote_followings_inc, ___remote_followings_dec, ___remote_followers_total, ___remote_followers_inc, ___remote_followers_dec) FROM stdin;
1	1698364800	9lcb5iw3qm0zyytv	1	1	0	1	1	0	0	0	0	0	0	0
2	1698364800	9lcc5uu4cug6mxgv	0	0	0	0	0	0	1	1	0	1	1	0
\.


--
-- Data for Name: __chart_day__per_user_notes; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__per_user_notes (id, date, "group", ___total, ___inc, ___dec, ___diffs_normal, ___diffs_reply, ___diffs_renote, "___diffs_withFile") FROM stdin;
2	1698364800	9lcb5iw3qm0zyytv	3	3	0	3	0	0	0
1	1698364800	9lcc5uu4cug6mxgv	5	5	0	4	0	1	0
3	1698451200	9lcc5uu4cug6mxgv	6	1	0	0	0	1	0
4	1698451200	9lcb5iw3qm0zyytv	5	2	0	1	1	0	0
\.


--
-- Data for Name: __chart_day__per_user_reaction; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__per_user_reaction (id, date, "group", ___local_count, ___remote_count) FROM stdin;
1	1698364800	9lcb5iw3qm0zyytv	0	3
2	1698451200	9lcc5uu4cug6mxgv	2	0
\.


--
-- Data for Name: __chart_day__users; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.__chart_day__users (id, date, ___local_total, ___local_inc, ___local_dec, ___remote_total, ___remote_inc, ___remote_dec) FROM stdin;
1	1698364800	1	1	0	1	1	0
2	1698451200	2	0	0	1	0	0
3	1698537600	2	0	0	1	0	0
4	1698624000	2	0	0	1	0	0
\.


--
-- Data for Name: abuse_user_report; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.abuse_user_report (id, "createdAt", "targetUserId", "reporterId", "assigneeId", resolved, comment, "targetUserHost", "reporterHost", forwarded) FROM stdin;
\.


--
-- Data for Name: access_token; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.access_token (id, "createdAt", token, hash, "userId", "appId", "lastUsedAt", session, name, description, "iconUrl", permission, fetched) FROM stdin;
\.


--
-- Data for Name: ad; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.ad (id, "createdAt", "expiresAt", place, priority, url, "imageUrl", memo, ratio) FROM stdin;
\.


--
-- Data for Name: announcement; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.announcement (id, "createdAt", text, title, "imageUrl", "updatedAt", "showPopup", "isGoodNews") FROM stdin;
\.


--
-- Data for Name: announcement_read; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.announcement_read (id, "userId", "announcementId", "createdAt") FROM stdin;
\.


--
-- Data for Name: antenna; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.antenna (id, "createdAt", "userId", name, src, "userListId", keywords, "withFile", expression, notify, "caseSensitive", "withReplies", "userGroupJoiningId", users, "excludeKeywords", instances) FROM stdin;
\.


--
-- Data for Name: app; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.app (id, "createdAt", "userId", secret, name, description, permission, "callbackUrl") FROM stdin;
\.


--
-- Data for Name: attestation_challenge; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.attestation_challenge (id, "userId", challenge, "createdAt", "registrationChallenge") FROM stdin;
\.


--
-- Data for Name: auth_session; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.auth_session (id, "createdAt", token, "userId", "appId") FROM stdin;
\.


--
-- Data for Name: blocking; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.blocking (id, "createdAt", "blockeeId", "blockerId") FROM stdin;
\.


--
-- Data for Name: channel; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.channel (id, "createdAt", "lastNotedAt", "userId", name, description, "bannerId", "notesCount", "usersCount") FROM stdin;
\.


--
-- Data for Name: channel_following; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.channel_following (id, "createdAt", "followeeId", "followerId") FROM stdin;
\.


--
-- Data for Name: channel_note_pining; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.channel_note_pining (id, "createdAt", "channelId", "noteId") FROM stdin;
\.


--
-- Data for Name: clip; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.clip (id, "createdAt", "userId", name, "isPublic", description) FROM stdin;
\.


--
-- Data for Name: clip_note; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.clip_note (id, "noteId", "clipId") FROM stdin;
\.


--
-- Data for Name: drive_file; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.drive_file (id, "createdAt", "userId", "userHost", md5, name, type, size, comment, properties, "storedInternal", url, "thumbnailUrl", "webpublicUrl", "accessKey", "thumbnailAccessKey", "webpublicAccessKey", uri, src, "folderId", "isSensitive", "isLink", blurhash, "webpublicType", "requestHeaders", "requestIp", "maybeSensitive", "maybePorn") FROM stdin;
9lcvxwhbduwsrjnl	2023-10-28 00:45:49.871+00	9lcb5iw3qm0zyytv	\N	49d5d3e510d7e301bab1acbe96961050	ablobfoxbongohyper.png	image/apng	9376	\N	{"width": 128, "height": 128}	t	https://firefish.swampnet.club/files/256821ad-adda-4737-b37f-9f88cf884928	\N	\N	256821ad-adda-4737-b37f-9f88cf884928	thumbnail-3f5ef05f-bfbb-4994-bec7-3ed595c73390	webpublic-971cda33-d133-40c0-a177-c96cc4bb30d1	\N	\N	\N	f	f	yMKH~.0zBCwcNGt75R~ARP69Egw]xaIp0.wIJ.Rk-7$j$MJANK=cslxZae$$wIkW$fR+t6NGS$9u$*niS4X8RjS$9]OYwcs.OEjFs:	\N	\N	\N	f	f
\.


--
-- Data for Name: drive_folder; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.drive_folder (id, "createdAt", name, "userId", "parentId") FROM stdin;
\.


--
-- Data for Name: emoji; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.emoji (id, "updatedAt", name, host, "originalUrl", uri, type, aliases, category, "publicUrl", license, width, height) FROM stdin;
9lcfecxvk7lpkxck	2023-10-27 17:02:44.227+00	ablobfoxbongo	akkoma.swampnet.club	https://akkoma.swampnet.club/emoji/testpack/ablobfoxbongo.png	https://akkoma.swampnet.club/emoji/testpack/ablobfoxbongo.png	\N	{}	\N	https://akkoma.swampnet.club/emoji/testpack/ablobfoxbongo.png	\N	128	128
9lcvxwjhtthu5sgf	2023-10-28 00:45:49.949+00	ablobfoxbongohyper	\N	https://firefish.swampnet.club/files/256821ad-adda-4737-b37f-9f88cf884928	\N	image/apng	{}	\N	https://firefish.swampnet.club/files/256821ad-adda-4737-b37f-9f88cf884928	\N	128	128
\.


--
-- Data for Name: follow_request; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.follow_request (id, "createdAt", "followeeId", "followerId", "requestId", "followerHost", "followerInbox", "followerSharedInbox", "followeeHost", "followeeInbox", "followeeSharedInbox") FROM stdin;
\.


--
-- Data for Name: following; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.following (id, "createdAt", "followeeId", "followerId", "followerHost", "followerInbox", "followerSharedInbox", "followeeHost", "followeeInbox", "followeeSharedInbox") FROM stdin;
9lcc5z61p2jvcjmv	2023-10-27 15:32:14.281+00	9lcc5uu4cug6mxgv	9lcb5iw3qm0zyytv	\N	\N	\N	akkoma.swampnet.club	https://akkoma.swampnet.club/users/puzzler/inbox	https://akkoma.swampnet.club/inbox
9lcc5v9mpb1ox8px	2023-10-27 15:32:09.226+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	akkoma.swampnet.club	https://akkoma.swampnet.club/users/puzzler/inbox	https://akkoma.swampnet.club/inbox	\N	\N	\N
\.


--
-- Data for Name: gallery_like; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.gallery_like (id, "createdAt", "userId", "postId") FROM stdin;
\.


--
-- Data for Name: gallery_post; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.gallery_post (id, "createdAt", "updatedAt", title, description, "userId", "fileIds", "isSensitive", "likedCount", tags) FROM stdin;
\.


--
-- Data for Name: hashtag; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.hashtag (id, name, "mentionedUserIds", "mentionedUsersCount", "mentionedLocalUserIds", "mentionedLocalUsersCount", "mentionedRemoteUserIds", "mentionedRemoteUsersCount", "attachedUserIds", "attachedUsersCount", "attachedLocalUserIds", "attachedLocalUsersCount", "attachedRemoteUserIds", "attachedRemoteUsersCount") FROM stdin;
\.


--
-- Data for Name: instance; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.instance (id, "caughtAt", host, "usersCount", "notesCount", "followingCount", "followersCount", "latestRequestSentAt", "latestStatus", "latestRequestReceivedAt", "lastCommunicatedAt", "isNotResponding", "softwareName", "softwareVersion", "openRegistrations", name, description, "maintainerName", "maintainerEmail", "infoUpdatedAt", "isSuspended", "iconUrl", "themeColor", "faviconUrl") FROM stdin;
9lcc5uwgp69wxj4y	2023-10-27 15:32:08.752+00	akkoma.swampnet.club	1	6	1	1	2023-10-28 01:08:26.476+00	200	2023-10-28 01:27:43.736+00	2023-10-28 01:27:43.736+00	f	akkoma	3.10.4-0-gebfb617	t	akkoma.swampnet.club	Akkoma: The cooler fediverse server	\N	\N	2023-10-27 15:32:09.117+00	f	https://akkoma.swampnet.club/static/logo.svg	#593196	https://akkoma.swampnet.club/favicon.png
\.


--
-- Data for Name: messaging_message; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.messaging_message (id, "createdAt", "userId", "recipientId", text, "isRead", "fileId", "groupId", reads, uri) FROM stdin;
\.


--
-- Data for Name: meta; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.meta (id, name, description, "maintainerName", "maintainerEmail", "disableRegistration", "disableLocalTimeline", "disableGlobalTimeline", "useStarForReactionFallback", langs, "hiddenTags", "blockedHosts", "mascotImageUrl", "bannerUrl", "errorImageUrl", "iconUrl", "cacheRemoteFiles", "enableRecaptcha", "recaptchaSiteKey", "recaptchaSecretKey", "localDriveCapacityMb", "remoteDriveCapacityMb", "summalyProxy", "enableEmail", email, "smtpSecure", "smtpHost", "smtpPort", "smtpUser", "smtpPass", "enableServiceWorker", "swPublicKey", "swPrivateKey", "enableTwitterIntegration", "twitterConsumerKey", "twitterConsumerSecret", "enableGithubIntegration", "githubClientId", "githubClientSecret", "enableDiscordIntegration", "discordClientId", "discordClientSecret", "pinnedUsers", "ToSUrl", "repositoryUrl", "feedbackUrl", "useObjectStorage", "objectStorageBucket", "objectStoragePrefix", "objectStorageBaseUrl", "objectStorageEndpoint", "objectStorageRegion", "objectStorageAccessKey", "objectStorageSecretKey", "objectStoragePort", "objectStorageUseSSL", "proxyAccountId", "objectStorageUseProxy", "enableHcaptcha", "hcaptchaSiteKey", "hcaptchaSecretKey", "objectStorageSetPublicRead", "pinnedPages", "backgroundImageUrl", "logoImageUrl", "pinnedClipId", "objectStorageS3ForcePathStyle", "allowedHosts", "secureMode", "privateMode", "deeplAuthKey", "deeplIsPro", "emailRequiredForSignup", "themeColor", "defaultLightTheme", "defaultDarkTheme", "sensitiveMediaDetection", "sensitiveMediaDetectionSensitivity", "setSensitiveFlagAutomatically", "enableIpLogging", "enableSensitiveMediaDetectionForVideos", "enableActiveEmailValidation", "customMOTD", "customSplashIcons", "disableRecommendedTimeline", "recommendedInstances", "enableGuestTimeline", "defaultReaction", "libreTranslateApiUrl", "libreTranslateApiKey", "silencedHosts", "experimentalFeatures", "enableServerMachineStats", "enableIdenticonGeneration", "donationLink") FROM stdin;
x	\N	\N	\N	\N	f	f	f	f	{}	{}	{}	/static-assets/badges/info.png	\N	/static-assets/badges/error.png	\N	f	f	\N	\N	1024	32	\N	f	\N	f	\N	\N	\N	\N	f	\N	\N	f	\N	\N	f	\N	\N	f	\N	\N	{}	\N	https://codeberg.org/firefish/firefish	https://codeberg.org/firefish/firefish/issues	f	\N	\N	\N	\N	\N	\N	\N	\N	t	\N	t	f	\N	\N	f	{/featured,/channels,/explore,/pages,/about-misskey}	\N	\N	\N	t	{}	f	f	\N	f	f	\N	\N	\N	none	medium	f	f	f	t	{}	{}	t	{}	f	⭐	\N	\N	{}	{}	f	t	\N
\.


--
-- Data for Name: migrations; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.migrations (id, "timestamp", name) FROM stdin;
1	1000000000000	Init1000000000000
2	1556348509290	Pages1556348509290
3	1556746559567	UserProfile1556746559567
4	1557476068003	PinnedUsers1557476068003
5	1557761316509	AddSomeUrls1557761316509
6	1557932705754	ObjectStorageSetting1557932705754
7	1558072954435	PageLike1558072954435
8	1558103093633	UserGroup1558103093633
9	1558257926829	UserGroupInvite1558257926829
10	1558266512381	UserListJoining1558266512381
11	1561706992953	webauthn1561706992953
12	1561873850023	ChartIndexes1561873850023
13	1562422242907	PasswordLessLogin1562422242907
14	1562444565093	PinnedPage1562444565093
15	1562448332510	PageTitleHideOption1562448332510
16	1562869971568	ModerationLog1562869971568
17	1563757595828	UsedUsername1563757595828
18	1565634203341	room1565634203341
19	1571220798684	CustomEmojiCategory1571220798684
20	1572760203493	nodeinfo1572760203493
21	1576269851876	TalkFederationId1576269851876
22	1576869585998	ProxyRemoteFiles1576869585998
23	1579267006611	v121579267006611
24	1579270193251	v1221579270193251
25	1579282808087	v1231579282808087
26	1579544426412	v1241579544426412
27	1579977526288	v1251579977526288
28	1579993013959	v1261579993013959
29	1580069531114	v1271580069531114
30	1580148575182	v1281580148575182
31	1580154400017	v1291580154400017
32	1580276619901	v12101580276619901
33	1580331224276	v12111580331224276
34	1580508795118	v12121580508795118
35	1580543501339	v12131580543501339
36	1580864313253	v12141580864313253
37	1581526429287	userGroupInvitation1581526429287
38	1581695816408	userGroupAntenna1581695816408
39	1581708415836	driveUserFolderIdIndex1581708415836
40	1581979837262	promo1581979837262
41	1582019042083	featuredInjecttion1582019042083
42	1582210532752	antennaExclude1582210532752
43	1582875306439	noteReactionLength1582875306439
44	1585361548360	miauth1585361548360
45	1585385921215	customNotification1585385921215
46	1585772678853	apUrl1585772678853
47	1586624197029	AddObjectStorageUseProxy1586624197029
48	1586641139527	remoteReaction1586641139527
49	1586708940386	pageAiScript1586708940386
50	1588044505511	hCaptcha1588044505511
51	1589023282116	pubRelay1589023282116
52	1595075960584	blurhash1595075960584
53	1595077605646	blurhashForAvatarBanner1595077605646
54	1595676934834	instanceIconUrl1595676934834
55	1595771249699	wordMute1595771249699
56	1595782306083	wordMute21595782306083
57	1596548170836	channel1596548170836
58	1596786425167	channel21596786425167
59	1597230137744	objectStorageSetPublicRead1597230137744
60	1597236229720	IncludingNotificationTypes1597236229720
61	1597385880794	addSensitiveIndex1597385880794
62	1597459042300	channelUnread1597459042300
63	1597893996136	ChannelNoteIdDescIndex1597893996136
64	1600353287890	mutingNotificationTypes1600353287890
65	1603094348345	refineAbuseUserReport1603094348345
66	1603095701770	refineAbuseUserReport21603095701770
67	1603776877564	instanceThemeColor1603776877564
68	1603781553011	instanceFavicon1603781553011
69	1604821689616	deleteAutoWatch1604821689616
70	1605408848373	clipDescription1605408848373
71	1605408971051	comments1605408971051
72	1605585339718	instancePinnedPages1605585339718
73	1605965516823	instanceImages1605965516823
74	1606191203881	noCrawle1606191203881
75	1607151207216	instancePinnedClip1607151207216
76	1607353487793	isExplorable1607353487793
77	1610277136869	registry1610277136869
78	1610277585759	registry21610277585759
79	1610283021566	registry31610283021566
80	1611354329133	followersUri1611354329133
81	1611397665007	gallery1611397665007
82	1611547387175	objectStorageS3ForcePathStyle1611547387175
83	1612619156584	announcementEmail1612619156584
84	1613155914446	emailNotificationTypes1613155914446
85	1613181457597	userLang1613181457597
86	1613503367223	useBigintForDriveUsage1613503367223
87	1615965918224	chartV21615965918224
88	1615966519402	chartV221615966519402
89	1618637372000	userLastActiveDate1618637372000
90	1618639857000	userHideOnlineStatus1618639857000
91	1619942102890	passwordReset1619942102890
92	1620019354680	ad1620019354680
93	1620364649428	ad21620364649428
94	1621479946000	addNoteIndexes1621479946000
95	1622679304522	userProfileDescriptionLength1622679304522
96	1622681548499	logMessageLength1622681548499
97	1626509500668	fixRemoteFileProxy1626509500668
98	1626733991004	allowlistSecureMode1626733991004
99	1629004542760	chartReindex1629004542760
100	1629024377804	deeplIntegration1629024377804
101	1629288472000	fixChannelUserId1629288472000
102	1629512953000	isUserDeleted1629512953000
103	1629778475000	deeplIntegration21629778475000
104	1629833361000	addShowTLReplies1629833361000
105	1629968054000	userInstanceBlocks1629968054000
106	1633068642000	emailRequiredForSignup1633068642000
107	1633071909016	userPending1633071909016
108	1634486652000	userPublicReactions1634486652000
109	1634902659689	deleteLog1634902659689
110	1635500777168	noteThreadMute1635500777168
111	1636197624383	ffVisibility1636197624383
112	1636697408073	removeViaMobile1636697408073
113	1637320813000	forwardedReport1637320813000
114	1639325650583	chartV31639325650583
115	1642611822809	emojiUrl1642611822809
116	1642613870898	driveFileWebpublicType1642613870898
117	1643963705770	chartV41643963705770
118	1643966656277	chartV51643966656277
119	1643967331284	chartV61643967331284
120	1644010796173	convertHardMutes1644010796173
121	1644058404077	chartV71644058404077
122	1644059847460	chartV81644059847460
123	1644060125705	chartV91644060125705
124	1644073149413	chartV101644073149413
125	1644095659741	chartV111644095659741
126	1644328606241	chartV121644328606241
127	1644331238153	chartV131644331238153
128	1644344266289	chartV141644344266289
129	1644395759931	instanceThemeColor1644395759931
130	1644481657998	chartV151644481657998
131	1644551208096	followingIndexes1644551208096
132	1645340161439	removeMaxNoteTextLength1645340161439
133	1645599900873	federationChartPubsub1645599900873
134	1646143552768	instanceDefaultTheme1646143552768
135	1646387162108	muteExpiresAt1646387162108
136	1646549089451	pollEndedNotification1646549089451
137	1646633030285	chartFederationActive1646633030285
138	1646655454495	removeInstanceDriveColumns1646655454495
139	1646732390560	chartFederationActiveSubPub1646732390560
140	1648548247382	webhook1648548247382
141	1648816172177	webhook21648816172177
142	1651224615271	foreignKeyReports1651224615271
143	1652859567549	uniformThemecolor1652859567549
144	1655368940105	nsfwDetection1655368940105
145	1655371960534	nsfwDetection21655371960534
146	1655388169582	nsfwDetection31655388169582
147	1655393015659	nsfwDetection41655393015659
148	1655813815729	driveCapacityOverrideMb1655813815729
149	1655918165614	userIp1655918165614
150	1656122560740	fileIp1656122560740
151	1656251734807	nsfwDetection51656251734807
152	1656328812281	ip21656328812281
153	1656408772602	nsfwDetection61656408772602
154	1656772790599	userModerationNote1656772790599
155	1657346559800	activeEmailValidation1657346559800
156	1658203170545	calckey1658203170545
157	1658656633972	noteRepliesFunction1658656633972
158	1658939464003	CustomMOTD1658939464003
159	1658941974648	CustomSplashIcons1658941974648
160	1658981842728	FixFirefish1658981842728
161	1659042130648	RecommendedTimeline1659042130648
162	1660068273737	GuestTimeline1660068273737
163	1665091090561	addRenoteMuting1665091090561
164	1668828368510	Page1668828368510
165	1668831378728	FixFirefishAgain1668831378728
166	1669138716634	whetherPushNotifyToSendReadMessage1669138716634
167	1669288094000	addMovedToAndKnownAs1669288094000
168	1671199573000	addFkAbuseUserReportTargetUserIdToUserId1671199573000
169	1671388343000	FirefishRepoMove1671388343000
170	1672882664294	DefaultReaction1672882664294
171	1673336077243	PollChoiceLength1673336077243
172	1676093997212	AntennaInstances1676093997212
173	1677935903517	DriveComment1677935903517
174	1678426061773	tweakVarcharLength1678426061773
175	1678945242650	addPropsForCustomEmoji1678945242650
176	1679269929000	FixRepo1679269929000
177	1680375641101	CleanCharts1680375641101
178	1680426269172	SpeakAsCat1680426269172
179	1682753227899	NoteEdit1682753227899
180	1682777547198	LibreTranslate1682777547198
181	1682891890317	InstanceSilence1682891890317
182	1682891891317	AddHiddenPosts1682891891317
183	1683682889948	PreventAiLearning1683682889948
184	1683980686995	ExperimentalFeatures1683980686995
185	1684206886988	RemoveShowTimelineReplies1684206886988
186	1684494870830	EmojiSize1684494870830
187	1688280713783	AddMetaOptions1688280713783
188	1688845537045	AnnouncementPopup1688845537045
189	1689136347561	DonationLink1689136347561
190	1689739513827	FirefishRepo1689739513827
191	1689957674000	FirefishRepo1689957674000
\.


--
-- Data for Name: moderation_log; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.moderation_log (id, "createdAt", "userId", type, info) FROM stdin;
9lcvxwjmb16g5h20	2023-10-28 00:45:49.954+00	9lcb5iw3qm0zyytv	addEmoji	{"emojiId": "9lcvxwjhtthu5sgf"}
\.


--
-- Data for Name: muted_note; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.muted_note (id, "noteId", "userId", reason) FROM stdin;
\.


--
-- Data for Name: muting; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.muting (id, "createdAt", "muteeId", "muterId", "expiresAt") FROM stdin;
\.


--
-- Data for Name: note; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note (id, "createdAt", "replyId", "renoteId", text, name, cw, "userId", "localOnly", "renoteCount", "repliesCount", reactions, visibility, uri, score, "fileIds", "attachedFileTypes", "visibleUserIds", mentions, "mentionedRemoteUsers", emojis, tags, "hasPoll", "userHost", "replyUserId", "replyUserHost", "renoteUserId", "renoteUserHost", url, "channelId", "threadId", "updatedAt") FROM stdin;
9lcedao6u12pod10	2023-10-27 16:33:55.014+00	\N	\N	test post	\N	\N	9lcc5uu4cug6mxgv	f	0	0	{}	public	https://akkoma.swampnet.club/objects/520b8921-5f7b-4ca0-953f-2be9ace7caed	0	{}	{}	{}	{}	[]	{}	{}	f	akkoma.swampnet.club	\N	\N	\N	\N	\N	\N	\N	\N
9lcf8acmiij7bptp	2023-10-27 16:58:00.934+00	\N	\N	test2	\N	\N	9lcc5uu4cug6mxgv	f	0	0	{}	public	https://akkoma.swampnet.club/objects/891da966-b513-4f63-9d2a-99dcc29ca78d	0	{}	{}	{}	{}	[]	{}	{}	f	akkoma.swampnet.club	\N	\N	\N	\N	\N	\N	\N	\N
9lcwn1z9vfiszrhs	2023-10-28 01:05:23.397+00	9lcwkhgjnn4zu9b3	\N	*why is this so bad* @puzzler@akkoma.swampnet.club	\N	waaaaaaaa	9lcb5iw3qm0zyytv	f	0	0	{}	home	\N	0	{}	{}	{}	{9lcc5uu4cug6mxgv}	[{"username":"puzzler","host":"akkoma.swampnet.club","uri":"https://akkoma.swampnet.club/users/puzzler","url":"https://akkoma.swampnet.club/users/puzzler"}]	{}	{}	f	\N	9lcb5iw3qm0zyytv	\N	\N	\N	\N	\N	9lcwkhgjnn4zu9b3	2023-10-28 01:07:18.488+00
9lcffpj2s6kg03mq	2023-10-27 17:03:47.198+00	\N	9lceep04yz5lkdqi	\N	\N	\N	9lcc5uu4cug6mxgv	f	0	0	{}	public	https://akkoma.swampnet.club/activities/1ffe7f42-456b-42dd-906e-8a3e31fc7595	0	{}	{}	{}	{}	[]	{}	{}	f	akkoma.swampnet.club	\N	\N	9lcb5iw3qm0zyytv	\N	\N	\N	\N	\N
9lceep04yz5lkdqi	2023-10-27 16:35:00.244+00	\N	\N	test	\N	\N	9lcb5iw3qm0zyytv	f	1	0	{"⭐": 1, ":ablobfoxbongo@akkoma.swampnet.club:": 0}	public	\N	2	{}	{}	{}	{}	[]	{}	{}	f	\N	\N	\N	\N	\N	\N	\N	\N	\N
9lcfzvqfp8f3gnl9	2023-10-27 17:19:28.359+00	\N	\N	sghdfhfsdfhsdhfs	\N	\N	9lcc5uu4cug6mxgv	f	0	0	{}	public	https://akkoma.swampnet.club/objects/80442f53-4051-4f20-b0ea-dcfce3d1847b	0	{}	{}	{}	{}	[]	{}	{}	f	akkoma.swampnet.club	\N	\N	\N	\N	\N	\N	\N	\N
9lcg12mplgcqpiro	2023-10-27 17:20:23.953+00	\N	\N	What service do you use? :ablobfoxbongo:	\N	\N	9lcc5uu4cug6mxgv	f	0	0	{}	public	https://akkoma.swampnet.club/objects/a176c59a-6fa9-46a6-9322-a99866f380be	0	{}	{}	{}	{}	[]	{ablobfoxbongo}	{}	t	akkoma.swampnet.club	\N	\N	\N	\N	\N	\N	\N	\N
9lcfyd96rjwpw3u8	2023-10-27 17:18:17.754+00	\N	\N	beeeeee	\N	\N	9lcb5iw3qm0zyytv	f	0	0	{"🧑": 1}	public	\N	1	{}	{}	{}	{}	[]	{}	{}	f	\N	\N	\N	\N	\N	\N	\N	\N	\N
9lcdwhj8ho43021q	2023-10-27 16:20:50.756+00	\N	\N	test post	\N	\N	9lcb5iw3qm0zyytv	f	1	0	{}	public	\N	1	{}	{}	{}	{}	[]	{}	{}	f	\N	\N	\N	\N	\N	\N	\N	\N	\N
9lcvjiz4j9hdf6oa	2023-10-28 00:34:39.184+00	\N	9lcdwhj8ho43021q	@puzzler@firefish.swampnet.club testquote\n\nRE: https://firefish.swampnet.club/notes/9lcdwhj8ho43021q	\N	cw	9lcc5uu4cug6mxgv	f	0	0	{"⭐": 0, ":ablobfoxbongohyper:": 1}	public	https://akkoma.swampnet.club/objects/d4be167d-e9c0-4748-8efc-54384950958f	1	{}	{}	{}	{9lcb5iw3qm0zyytv}	[]	{}	{}	f	akkoma.swampnet.club	\N	\N	9lcb5iw3qm0zyytv	\N	\N	\N	\N	\N
9lcwkhgjnn4zu9b3	2023-10-28 01:03:23.491+00	\N	\N	ActivityPub is a mess holy shit @puzzler@akkoma.swampnet.club	\N	waaaaaaaa	9lcb5iw3qm0zyytv	f	0	1	{}	followers	\N	0	{}	{}	{}	{9lcc5uu4cug6mxgv}	[{"uri":"https://akkoma.swampnet.club/users/puzzler","url":"https://akkoma.swampnet.club/users/puzzler","username":"puzzler","host":"akkoma.swampnet.club"}]	{}	{}	f	\N	\N	\N	\N	\N	\N	\N	\N	\N
\.


--
-- Data for Name: note_edit; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_edit (id, "noteId", text, cw, "fileIds", "updatedAt") FROM stdin;
9lcwpisbwznx3xuc	9lcwn1z9vfiszrhs	*why is this so bad* @puzzler@akkoma.swampnet.club	waaaaaaaa	{}	2023-10-28 01:07:18.491+00
\.


--
-- Data for Name: note_favorite; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_favorite (id, "createdAt", "userId", "noteId") FROM stdin;
\.


--
-- Data for Name: note_reaction; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_reaction (id, "createdAt", "userId", "noteId", reaction) FROM stdin;
9lcfx5wxss8uxjw1	2023-10-27 17:17:21.585+00	9lcc5uu4cug6mxgv	9lceep04yz5lkdqi	⭐
9lcg5f4jux3fvapw	2023-10-27 17:23:46.772+00	9lcc5uu4cug6mxgv	9lcfyd96rjwpw3u8	🧑
9lcvytm2l12ruolj	2023-10-28 00:46:32.81+00	9lcb5iw3qm0zyytv	9lcvjiz4j9hdf6oa	:ablobfoxbongohyper:
\.


--
-- Data for Name: note_thread_muting; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_thread_muting (id, "createdAt", "userId", "threadId") FROM stdin;
\.


--
-- Data for Name: note_unread; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_unread (id, "userId", "noteId", "noteUserId", "isSpecified", "isMentioned", "noteChannelId") FROM stdin;
\.


--
-- Data for Name: note_watching; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.note_watching (id, "createdAt", "userId", "noteId", "noteUserId") FROM stdin;
\.


--
-- Data for Name: notification; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.notification (id, "createdAt", "notifieeId", "notifierId", "isRead", "noteId", reaction, choice, "followRequestId", type, "userGroupInvitationId", "customBody", "customHeader", "customIcon", "appAccessTokenId") FROM stdin;
9lcc5vatgtq1gpdu	2023-10-27 15:32:09.269+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	\N	\N	\N	\N	follow	\N	\N	\N	\N	\N
9lcc5z6djre4386u	2023-10-27 15:32:14.293+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	\N	\N	\N	\N	followRequestAccepted	\N	\N	\N	\N	\N
9lcgdxlrey4hwco4	2023-10-27 17:30:23.967+00	9lcc5uu4cug6mxgv	\N	f	9lcg12mplgcqpiro	\N	\N	\N	pollEnded	\N	\N	\N	\N	\N
9lcfecysed6pn6ul	2023-10-27 17:02:44.26+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	9lceep04yz5lkdqi	:ablobfoxbongo@akkoma.swampnet.club:	\N	\N	reaction	\N	\N	\N	\N	\N
9lcffppak96xa517	2023-10-27 17:03:47.422+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	9lcffpj2s6kg03mq	\N	\N	\N	renote	\N	\N	\N	\N	\N
9lcfx5ykcsgeg2ja	2023-10-27 17:17:21.644+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	9lceep04yz5lkdqi	⭐	\N	\N	reaction	\N	\N	\N	\N	\N
9lcg5f5eji8800rz	2023-10-27 17:23:46.802+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	9lcfyd96rjwpw3u8	🧑	\N	\N	reaction	\N	\N	\N	\N	\N
9lcvjjmsgskkod2e	2023-10-28 00:34:40.036+00	9lcb5iw3qm0zyytv	9lcc5uu4cug6mxgv	t	9lcvjiz4j9hdf6oa	\N	\N	\N	quote	\N	\N	\N	\N	\N
\.


--
-- Data for Name: page; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.page (id, "createdAt", "updatedAt", title, name, summary, "alignCenter", font, "userId", "eyeCatchingImageId", content, variables, visibility, "visibleUserIds", "likedCount", "hideTitleWhenPinned", script, "isPublic") FROM stdin;
\.


--
-- Data for Name: page_like; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.page_like (id, "createdAt", "userId", "pageId") FROM stdin;
\.


--
-- Data for Name: password_reset_request; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.password_reset_request (id, "createdAt", token, "userId") FROM stdin;
\.


--
-- Data for Name: poll; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.poll ("noteId", "expiresAt", multiple, choices, votes, "noteVisibility", "userId", "userHost") FROM stdin;
9lcg12mplgcqpiro	2023-10-27 17:30:23.952+00	t	{Masto,Fire,koma,goto}	{0,0,0,0}	public	9lcc5uu4cug6mxgv	akkoma.swampnet.club
\.


--
-- Data for Name: poll_vote; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.poll_vote (id, "createdAt", "userId", "noteId", choice) FROM stdin;
\.


--
-- Data for Name: promo_note; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.promo_note ("noteId", "expiresAt", "userId") FROM stdin;
\.


--
-- Data for Name: promo_read; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.promo_read (id, "createdAt", "userId", "noteId") FROM stdin;
\.


--
-- Data for Name: registration_ticket; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.registration_ticket (id, "createdAt", code) FROM stdin;
\.


--
-- Data for Name: registry_item; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.registry_item (id, "createdAt", "updatedAt", "userId", key, scope, domain, value) FROM stdin;
9lcb5mxzkvn0kpof	2023-10-27 15:03:58.823+00	2023-10-27 16:34:55.246+00	9lcb5iw3qm0zyytv	tutorial	{client,base}	\N	-1
\.


--
-- Data for Name: relay; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.relay (id, inbox, status) FROM stdin;
\.


--
-- Data for Name: renote_muting; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.renote_muting (id, "createdAt", "muteeId", "muterId") FROM stdin;
\.


--
-- Data for Name: seaql_migrations; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.seaql_migrations (version, applied_at) FROM stdin;
m20230531_180824_drop_reversi	1698418769
m20230627_185451_index_note_url	1698418769
m20230709_000510_move_antenna_to_cache	1698418769
\.


--
-- Data for Name: signin; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.signin (id, "createdAt", "userId", ip, headers, success) FROM stdin;
\.


--
-- Data for Name: sw_subscription; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.sw_subscription (id, "createdAt", "userId", endpoint, auth, publickey, "sendReadMessage") FROM stdin;
\.


--
-- Data for Name: used_username; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.used_username (username, "createdAt") FROM stdin;
puzzler	2023-10-27 15:03:53.606+00
instance.actor	2023-10-27 15:32:05.974+00
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public."user" (id, "createdAt", "updatedAt", "lastFetchedAt", username, "usernameLower", name, "followersCount", "followingCount", "notesCount", "avatarId", "bannerId", tags, "isSuspended", "isSilenced", "isLocked", "isBot", "isCat", "isAdmin", "isModerator", emojis, host, inbox, "sharedInbox", featured, uri, token, "isExplorable", "followersUri", "lastActiveDate", "hideOnlineStatus", "isDeleted", "driveCapacityOverrideMb", "movedToUri", "alsoKnownAs", "speakAsCat") FROM stdin;
9lcb5iw3qm0zyytv	2023-10-27 15:03:53.572+00	2023-10-28 01:05:23.405+00	\N	puzzler	puzzler	\N	1	1	5	\N	\N	{}	f	f	f	f	t	t	f	{}	\N	\N	\N	\N	\N	UUCMcTlOaP9sW2Il	t	\N	2023-10-29 01:55:09.478+00	f	f	\N	\N	\N	t
9lcc5sqeb6qm95aa	2023-10-27 15:32:05.942+00	\N	\N	instance.actor	instance.actor	\N	0	0	0	\N	\N	{}	f	f	t	t	f	f	f	{}	\N	\N	\N	\N	\N	txFoyJUMn812gIkN	f	\N	\N	f	f	\N	\N	\N	t
9lcc5uu4cug6mxgv	2023-10-27 15:32:08.668+00	2023-10-28 00:34:39.921+00	2023-10-28 01:19:47.541+00	puzzler	puzzler	puzzler	1	1	6	\N	\N	{}	f	f	f	f	f	f	f	{}	akkoma.swampnet.club	https://akkoma.swampnet.club/users/puzzler/inbox	https://akkoma.swampnet.club/inbox	https://akkoma.swampnet.club/users/puzzler/collections/featured	https://akkoma.swampnet.club/users/puzzler	\N	f	https://akkoma.swampnet.club/users/puzzler/followers	\N	f	f	\N	\N		t
\.


--
-- Data for Name: user_group; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_group (id, "createdAt", name, "userId", "isPrivate") FROM stdin;
\.


--
-- Data for Name: user_group_invitation; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_group_invitation (id, "createdAt", "userId", "userGroupId") FROM stdin;
\.


--
-- Data for Name: user_group_invite; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_group_invite (id, "createdAt", "userId", "userGroupId") FROM stdin;
\.


--
-- Data for Name: user_group_joining; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_group_joining (id, "createdAt", "userId", "userGroupId") FROM stdin;
\.


--
-- Data for Name: user_ip; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_ip (id, "createdAt", "userId", ip) FROM stdin;
\.


--
-- Data for Name: user_keypair; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_keypair ("userId", "publicKey", "privateKey") FROM stdin;
9lcb5iw3qm0zyytv	-----BEGIN PUBLIC KEY-----\nMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAisfsjH/fSU589hkYX4Bk\nNtBKTp5nV6jhzl1aEOMLNsh3MivXR9YH1xx+195ZjCcTrW5XI8WuUGyZCSseREOo\nlqHkbyek0VU9QnkYvvaAcwby7kbjpkeu8Ei19CEd/RJw6EGWVqF4OJ3wWneSJRoZ\n/Xzklc9RXzFQWWLN5B9G4GR3RtxF0JZyOLn9bqVWljEh6LiclY3gxFq/jNX5BRA6\nDE0HUrTG7s+cXTRbi4ehIRMy4v0WurzRael8HbKRY/vBktAef1lcmncgOsOogD8x\n8DAglQbifZUREbN9gcLlSAc8kxF4nDBKjYw8yWmWo9xsF9tdZYz7wlGJmyRzXah0\n6XOU03ST7HE/PTPqFjvM+BW2c1VUqL/ltD2AfubISaTUhc5iNb4g4hbzoM8JU8lj\nXKIMxLqYD4xLLQyu7JoXS90zRkZAnqWBMUEtNMB5yQAI/z1YeWwkcQjULStijEYs\nVXiQNQujBVYuUzVd1qHAjDfvIItELqMoDbRLS4Ao8UmFmDYWHjQgHT0o6rj1fMVh\nHuSybsvGYPcZqUFTEwKkyxBqKesmemSZ6FstXu9UrXrVKMkTxHCEgciT4aUTNhFi\nNxRQlyRiugUAR773kZ4NaOYNr0K2I3/AFcdMmurb1LShwXV5CaHs8c5Biqf0Lfiu\n+tmfO79HdB2ie46o/nEuxo0CAwEAAQ==\n-----END PUBLIC KEY-----\n	-----BEGIN PRIVATE KEY-----\nMIIJQQIBADANBgkqhkiG9w0BAQEFAASCCSswggknAgEAAoICAQCKx+yMf99JTnz2\nGRhfgGQ20EpOnmdXqOHOXVoQ4ws2yHcyK9dH1gfXHH7X3lmMJxOtblcjxa5QbJkJ\nKx5EQ6iWoeRvJ6TRVT1CeRi+9oBzBvLuRuOmR67wSLX0IR39EnDoQZZWoXg4nfBa\nd5IlGhn9fOSVz1FfMVBZYs3kH0bgZHdG3EXQlnI4uf1upVaWMSHouJyVjeDEWr+M\n1fkFEDoMTQdStMbuz5xdNFuLh6EhEzLi/Ra6vNFp6XwdspFj+8GS0B5/WVyadyA6\nw6iAPzHwMCCVBuJ9lRERs32BwuVIBzyTEXicMEqNjDzJaZaj3GwX211ljPvCUYmb\nJHNdqHTpc5TTdJPscT89M+oWO8z4FbZzVVSov+W0PYB+5shJpNSFzmI1viDiFvOg\nzwlTyWNcogzEupgPjEstDK7smhdL3TNGRkCepYExQS00wHnJAAj/PVh5bCRxCNQt\nK2KMRixVeJA1C6MFVi5TNV3WocCMN+8gi0QuoygNtEtLgCjxSYWYNhYeNCAdPSjq\nuPV8xWEe5LJuy8Zg9xmpQVMTAqTLEGop6yZ6ZJnoWy1e71StetUoyRPEcISByJPh\npRM2EWI3FFCXJGK6BQBHvveRng1o5g2vQrYjf8AVx0ya6tvUtKHBdXkJoezxzkGK\np/Qt+K762Z87v0d0HaJ7jqj+cS7GjQIDAQABAoICAAaxwYhx3wb0H5Uz1uTW1ot9\nI6bdzDvCj6KuBrSu09eNXVz3SwSrkqg+0L2MyIJsT7r7cH4IwJfsWe6pYxkSFgh7\ns0wYIo94dNW6h3knfqdNzAHp1Ukr+jvPMUiFhzx+zoeqXKkKhx6QCJjy9wLajfgx\nm8mM/RT1Vej7gG82Tp7Vw2bD8ST9Kz673GyhOOdSZAGlhuQaumwuU0DlAQSZNXud\n0Zi2bXj1w2PZk5rluXeIRUAFMYeWqRfA5ZxKUbbiQ4JxKJXZbSnRGj+YOMFEix4J\nBeFUBv13NDuuDh1OyR/4cJfiU8OQ+5LbxpwRzFLuKu0OeorCylG9L8D8kqLcOxUF\n+3LMQL/4OnfEY9rbk77wCE49e56JBxUBzk33tsG7A0GuZsJS/DSqmZBHqVyFNMlx\nCRyuMq7JlgOm73qo/kxS2tbN6yMCIUmLBfBkaDIC+zrdngRnaWGS6Sx6yDPkm/6t\nyQZO0EmEi8jTDPGg+QKgBgG7ekmAn9Z9Ny+enwkHIF1MjTBdP2dCa81KIbadbaUk\n/j230hYODAgLnOu+9B6rFPIBfaUf4OSQfWRS0fz4YABSeEgBR43x2RHc99JknLrP\nNOa+GXL6tJSlNrLNH7l2+s9fZ+yhnow+z66fimtVA3x+hp38uCLeKHgtaEh7NehT\nfjZUUi+ctOEH676tsvAvAoIBAQDAOgJyH8r1ayJs6/XkANwiAxKud12LuvBrs6BX\ni1DW/CoiKJzMppWFwuD+fpL44PafEWt3s3xMIYcdJfkZ+4VSUJWVDMh7eHELOByx\n217KES5mqxt2H1yVTPRacDKvF6sNqiauzSO/sy6tBG5efSjxiEFkWAPLFp1ZICGV\nU5oSPcPEINo3liwMQBYlqsMwXU5nm4i9WkfBVea38NiFi1QmYQX9dKryQbZwReot\nDs3ZCk9mQzuhj/VykymrMrnAvSncm5C9L1XvsYOnxcGg28CD1AjI8YBwjXnayU65\nEaVJ3f+cgkiwzZtsMGtspwZxmfX1KqFwPdJwhwPs24dVPRUHAoIBAQC40rljRtp5\nhICUcY77gT4aSrz+VWBmfosYL8N0SUkGUjlyOWzg+tVbZLchsCSPMu35YaYJeTCO\nN/IwnZO0PxK1GE9Xnqui8/D5xmZos4Q/X9zBQnLDJEhLfws2U36rbHj5I3rAGbDV\n52PjVQUWBFRQvYXuJXY8H3i1eH8ElqR7P0bBGaPlPFCVLmYb1LfSS5KJTWOFTsUN\nuLTz7EDEQkCFLtRCEiiHtquMyyDhJkB8fM0vS5WllhFP2vUD50I1pwir9dAGgxew\n5YNU9kqbLKubOHKTH3wShxLJ5mvx1DA5Y9xgF9tjBJleoV4dLiX5LtMCU1310kme\nr4K+Dumu65bLAoIBAG3M9PaxeGJFRw3GntcVMqju7m3f2vxk1M4AdddrycpzkbGf\nVymDAie80H4uWM922Qi1j5h5Wse14xLgTPDtfhMt2rOf8KiufIv3ClUBxJxRwhp9\nJJuNgHn70hwSuLDRKTEN/zicDw6CUGgd/tlUTt3ggdHKgKgyC8EMHILKlVhUFY3j\nYiKcUh7RQJVVfFcVBSMjcbEPb6tc4QQyu8tUccXWZ/CGAtuMc6SgGwqPyT9vpzj3\nsIkxzPccXpHe0MHqi43rAns+Ha1jzZs418iFnervY/E8Gayw82jo0mHeVXbjKw4A\nUXtEhsH+ym3u3jxnwFnBdyPA/IKwbKqQHzauRGMCggEALO10iVCA7UcrZmpQYozh\nRsziMkUYwPGD6GY78fmamcHFGyIgeHQhBy1tYvAJgxHTTWrSTL0782ZgJjc333nx\nlzoIl3kHqf34lw8Gd+8jd7Ba3lPDx/H67Pc+B3+2a/9bTNy2CxpzpRuxaWKScO6u\nfE4Fz4Ulv6m60woufjQMWbVhArGz6JaH1CvKQHuuD521SlEYnyAtwI8k1aKZf9vH\nCVj/2evGFG9wNjJulKySwnAoBJYwE2duX+YO51sA+j/XWM3ROrQFP0gba7zPF/d8\n46eOGntt0qcpBxFPLiLxchH3URibiO9mRN2fHiLIslESlB8l2Qq8QKnTc1X2skGs\njwKCAQBQcETAaxfuKLExYKORohd/F7yV2LgzxXUTWFDHY+i2Jsx2y3Tveegb586B\nixFn8oPLXAKrrnjOQe1rzlVanTulSwRb2n3ATpq7hEte7m+q7Hbtb1zQPZFZeoe5\nyFV3ZJsVNszy1jcFmiwbonuXkuz0aoJ70/YytZi2NVUsXOfoRyfDq4GX/HVNpE8I\nK6/pJ4V2b6qOOSb0v+mqphfOdrB1aJIW0+fJZDjkW5JOFGvr2KeSnCwfIUfPJJxm\nkTRbR4DKA8EPaWLr+wd2A0/vrI+gYFQBqfl11KmEaidOd/GyDXTKRjZBtrLVL1sN\nxgdolOiyksvLn6EDK3e5tP1LNIn6\n-----END PRIVATE KEY-----\n
9lcc5sqeb6qm95aa	-----BEGIN PUBLIC KEY-----\nMIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEArkKcgD1++ILI0WxQGQdj\nCSXLiRaJe5LQQgDmtc7PrhGuXu/HpDH6/ywkwji3aY+rjwobdvtzBuMi+3/sWqM4\npw8kbPQqYCyBgSqUgmJug/p0tS5qugCEV8miyMtFgcjb4V4Qd9/XI5dcbgtcyxZ6\nubiGCWo6CTIjwBPKkj0uUTZUX6O1UJMc17SuOSo5pOFfhpwISWaAiKlvo5/y9jGC\nrmbcK2JHAL+FLLJBKALzjjrcfAzwLyKwQJeTB+xu5DlBCkl6lK5I1oKuue1SpWf7\nLdxx181dH96QqyRdbsPU9snw/jLnmw5iiB8otOuVoQ8tTFxeo0oFVxMqzCjxOqcm\nASN/827aL0w3h5JpCkx6i10kQocVvZbZDHRBEBfZmljU3XZFHGhJd0ZZ8WjXr828\nKQU4VX2z/1oEBWDV8caFRxOS5FyyDj5s5gz2PCKuGpfpHYahIuAeUTV0ZHvovmft\ntd3ACLs4i/d8yjMUJU6cpLPyrGkoPg0zu3DYhnUemU3T+5YMd7vf6WSp8gN+q6zM\nUpb7IAZ8FBEymeJihu/G6kIAzEnJcob4clioMWZNTN5IMqwrKMhnBPnlCAf94Vp/\nE0ltMoLPtcOHRnUXeLEgQF5H972+yWfPyMWAx3zOiTFD4PVTPxwvMuBkOqq8TeCA\nYcC/bYEjC72OeTt1KsEiNBECAwEAAQ==\n-----END PUBLIC KEY-----\n	-----BEGIN PRIVATE KEY-----\nMIIJQgIBADANBgkqhkiG9w0BAQEFAASCCSwwggkoAgEAAoICAQCuQpyAPX74gsjR\nbFAZB2MJJcuJFol7ktBCAOa1zs+uEa5e78ekMfr/LCTCOLdpj6uPCht2+3MG4yL7\nf+xaozinDyRs9CpgLIGBKpSCYm6D+nS1Lmq6AIRXyaLIy0WByNvhXhB339cjl1xu\nC1zLFnq5uIYJajoJMiPAE8qSPS5RNlRfo7VQkxzXtK45Kjmk4V+GnAhJZoCIqW+j\nn/L2MYKuZtwrYkcAv4UsskEoAvOOOtx8DPAvIrBAl5MH7G7kOUEKSXqUrkjWgq65\n7VKlZ/st3HHXzV0f3pCrJF1uw9T2yfD+MuebDmKIHyi065WhDy1MXF6jSgVXEyrM\nKPE6pyYBI3/zbtovTDeHkmkKTHqLXSRChxW9ltkMdEEQF9maWNTddkUcaEl3Rlnx\naNevzbwpBThVfbP/WgQFYNXxxoVHE5LkXLIOPmzmDPY8Iq4al+kdhqEi4B5RNXRk\ne+i+Z+213cAIuziL93zKMxQlTpyks/KsaSg+DTO7cNiGdR6ZTdP7lgx3u9/pZKny\nA36rrMxSlvsgBnwUETKZ4mKG78bqQgDMSclyhvhyWKgxZk1M3kgyrCsoyGcE+eUI\nB/3hWn8TSW0ygs+1w4dGdRd4sSBAXkf3vb7JZ8/IxYDHfM6JMUPg9VM/HC8y4GQ6\nqrxN4IBhwL9tgSMLvY55O3UqwSI0EQIDAQABAoICAA2qBvwOS73AVu9cVdE0m/6B\nt8VINApxeheRhbBlZwcK92LrMeV35CrXTbWWEsRkrIrtkQxCI3mmMgjJteFAkbwV\nyvZ+fzEl5EIaMjrMGEbh4Ci8hZ1lViX3whDf0ZOKShA7WuwPbF8BKtFX3j7wLw+P\nlCEPsufO25tKN1Tta7ZDXe0sQ4GDMPHgHCuQL6TJuv7C5D/J2Wkol2qw64avCK8q\nEnhrac5kS390UHl1oR6lZ8MUFPmujRFaNqsFI4fio8KNqElEJk19wfuDIWXQyBMc\nnqem6zcJHHZzBkmIcminRUNQKgrln0fLC7CDaur4wJr6K5M/G2PsdUC83XeK15xX\nnpjzDyvHz6lHvWUATBjE0Dvy3xNK0bJ3K1GMQIuayvS9+NSGeUip207y69krXD1+\n/unCAh703D5uhvCryHrymQ+sVfbcg5G+FPY7phPmiu7yTTkNe+6Jh7i8SMbpQGIV\nf9MF0YY9TR/xgeZJTwPBtzoZlF8pAfXuIOXh/k+3lGwDtW2SKPbpe/23nnfhkx1J\nBn2jUwvnLwCWcllrnvTn8Gb5oLnL6mtBtYXqEU2P97URaCtlj5d6WQwyd9wwBhxH\nySlFf902cH0OqLwzb4Phm4goGyhE2HfXmfd6O9g40YN1jriCkO23PX5rt/ibMRD4\nvXaJQR5tv0dDkymn2fO9AoIBAQDWTHJ6c1OuDYGglHGkzC73M4YecfCvKl1pvSUE\nCvfOMmDdyjSaPRqPAvqqdKPWBCx02kHJx9xdfyvg72M/sSiRIavsqU5lD1TjprEb\nX3JA2dUv1Pw+wHQgrBbk5b2/HC+5ToXZGDtPT6yxh2SK45VKb/2MQRMQQ9Pfet55\nZ9vhTeGXMiJxja/jDBZ34PSu7+v4bNk0QQ3HNiCQsios9h/k+rS0WfR7Oy4Zs+HT\nIztQnBB7wc7hAaT6p/MM9PwCwNd9eKUeLPOczFxynxjOn+IULY9ioUHFSCXyfduW\nO9xXukcmlejqOdBECrTYfsGuC8Pw7qV9ANP4GyFpQNmTQ8iNAoIBAQDQK5muNZKv\nG88E6ejg0Ksag8VzASfYWh6U3F0tYg01rtwt7bGrOmfxUp1YiS0kmmx/Nd30kIwy\n2IA9p+uATJ/6L9ky5iWFNpTiyTteCbbI7BzxGiuAgnkda8CpCDLX699TujqfT/k9\nF1ZwaJN88tB7roFfvNmLgz0CnDZFdOfuPvWtZfpuEQdQrWLPmfhl2Ql18jhVf7SM\n4q0uFxsmliGANSKWYExRfu4Q9dUW67vqJGux9WGShtygiHa0zLof+MTN1JewTsh2\nebanjdK5CAa5ONHPNU56r57eZURFwVNtGOL/AaXJZ/5V1UUdyZwYzM61HQG3k0/L\nHGfGD6yequKVAoIBAEvYVLAH/zbb6uQFhc84wOqW3sIJu5CTYupYmjA7HYdqBySY\nO1PXin3RJRyNGatWYNGeqagbx1+D8eizcSIKtnvfOfUR2wAjk/zac1Znyo0vS6eZ\nLwmanb8uiQ/MTALGnBFuvCqgy+T/IvTWIXySnbTxvAAWzAN2rhcram/3lTfBk7Uq\ndkxQRE8MaStWf+MmYRLUm29GEvVNr+rmU80D8zfcj3ZBXCj9QgywwJ00m/qksaTL\nbEfwiA56wKf2UPc8f0gWGgJ7EGGPZqzeqC4BIkfX9CaJQjjrlxqi9sPOPecCs8nx\nRrUXbyGV1HnHKw9JdLjiDcoXL+5g08tnu1nGpMUCggEAGEyemclDIBGc7rPnLSbu\nch/jknMWFZkfbLz0W3HQnO2WYf4W+SfOLks4drc8f8NO2uKM0Bl3ZBAFmCzbvpQz\nMF8ZeWmIA67FXlujBG0Z2wZEspgdb9jLDnYffRup465y2TEp2qLZMxpgSqrKXJFz\naJyMGYLP2gstufpGE4F7+UF8KAt5WhO+yNoUaKtOZYUaJ5xomwJXLwLeuDyWPMDV\nlEOfj/IZtoGchHpNr2lA/CnYMGO2SgZYBp0nTh9/sm+1eVlRg5EOsVWvzosth8+6\nCTDqvMc6K/QIFWm4uHPCqz92b9Hfak0rURWeBcYc5IAzuMx0ANu/OrRMIMB/o93K\nqQKCAQEAx+3sz+8rlq4LxJuSEISymbgAwaosl7gX4hsWD3lT2ZYLQ0vqKtnnhaSJ\nwYzK+Jja/kuMt4uiu953j5HV8mrKzGT1hWSahanxvgc+4ewZJt2qKa1OENwWY2Tr\nzIPPw5HlSdXZfYcteUSx87ISiXcOGLBYdYXbUnZQ8Tn1PkjJynR75kY6G+857IHC\nJSTp0wsEuyDi+b0thRN/7yZRcbG0T2GFWZdBqhshwmu38l7fvTbnmaRgM/okhC6g\n/Y4o8X7IUcWqlo7ejs6Ios4noeE/SLx1ti/l4S7fi28QJhv0jwH48gcY5r4Xm2fi\nIazAl/6EZMy754lflXcHBCBrGUSW/Q==\n-----END PRIVATE KEY-----\n
\.


--
-- Data for Name: user_list; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_list (id, "createdAt", "userId", name) FROM stdin;
\.


--
-- Data for Name: user_list_joining; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_list_joining (id, "createdAt", "userId", "userListId") FROM stdin;
\.


--
-- Data for Name: user_note_pining; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_note_pining (id, "createdAt", "userId", "noteId") FROM stdin;
\.


--
-- Data for Name: user_pending; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_pending (id, "createdAt", code, username, email, password) FROM stdin;
\.


--
-- Data for Name: user_profile; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_profile ("userId", location, birthday, description, fields, url, email, "emailVerifyCode", "emailVerified", "twoFactorTempSecret", "twoFactorSecret", "twoFactorEnabled", password, "clientData", "autoAcceptFollowed", "alwaysMarkNsfw", "carefulBot", "userHost", "securityKeysAvailable", "usePasswordLessLogin", "pinnedPageId", room, integrations, "injectFeaturedNote", "enableWordMute", "mutedWords", "mutingNotificationTypes", "noCrawle", "receiveAnnouncementEmail", "emailNotificationTypes", lang, "mutedInstances", "publicReactions", "ffVisibility", "autoSensitive", "moderationNote", "preventAiLearning") FROM stdin;
9lcc5sqeb6qm95aa	\N	\N	\N	[]	\N	\N	\N	f	\N	\N	f	$argon2id$v=19$m=65536,t=3,p=4$38zwaj9WjYKCNJp+M5YNMQ$C/eO95nlzF3C/PorXbBeyGWMlAk+4vxO1/UOME05+nE	{}	f	f	f	\N	f	f	\N	{}	{}	t	f	[]	{}	f	t	["follow", "receiveFollowRequest", "groupInvited"]	\N	[]	f	public	f		t
9lcb5iw3qm0zyytv	\N	\N	\N	[]	\N	\N	\N	f	\N	\N	f	$argon2id$v=19$m=65536,t=3,p=4$RaWn4aINWzF9vrcPCPT/Lg$ZLNt//dKXPfsB3xrzUzSFVC1O0wK66W7a4vAM/nbpGU	{}	t	f	f	\N	f	f	\N	{}	{}	t	f	[]	{}	f	t	["follow", "receiveFollowRequest", "groupInvited"]	\N	[]	f	public	f		t
9lcc5uu4cug6mxgv	\N	\N	YJTGFDYFGJT	[]	https://akkoma.swampnet.club/users/puzzler	\N	\N	f	\N	\N	f	\N	{}	f	f	f	akkoma.swampnet.club	f	f	\N	{}	{}	t	f	[]	{}	f	t	["follow", "receiveFollowRequest", "groupInvited"]	\N	[]	f	public	f		t
\.


--
-- Data for Name: user_publickey; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_publickey ("userId", "keyId", "keyPem") FROM stdin;
9lcc5uu4cug6mxgv	https://akkoma.swampnet.club/users/puzzler#main-key	-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAij5A2BrqU37x0Ik07hcR\nk1EMCAhQfVoDWTtwnWV3zdO7BMOWEtwFY8EmfBzQV0Ysw3pTUcEuQJ1mr3Mgh87R\n53FCceCuhQANtcYqgCDn9sAlP3SMOleoFh2EuG/aAvYrhAEmWV19SINWtfO6h8Cr\n7oVKeWooUoZiFpUZOwrO2Bg8gDF1WH31d0FNt7I+8ouadk8xtPDHdfrsygDHJyqc\nc2IdgzyfgWtD4AAr2kcFxQyLr7OxlijgSXbCqLhMUowflBFfcYpB49qzO23C5vjx\ntMxOxxGovpukuhwke7A/KANlOoB3w25lMpnB0MNNGvujMp1WOKmstJ4dakOJko1P\nawIDAQAB\n-----END PUBLIC KEY-----\n\n
\.


--
-- Data for Name: user_security_key; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.user_security_key (id, "userId", "publicKey", "lastUsed", name) FROM stdin;
\.


--
-- Data for Name: webhook; Type: TABLE DATA; Schema: public; Owner: calckey
--

COPY public.webhook (id, "createdAt", "userId", name, "on", url, secret, active, "latestSentAt", "latestStatus") FROM stdin;
\.


--
-- Name: __chart__active_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__active_users_id_seq', 4, true);


--
-- Name: __chart__ap_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__ap_request_id_seq', 5, true);


--
-- Name: __chart__drive_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__drive_id_seq', 1, true);


--
-- Name: __chart__federation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__federation_id_seq', 70, true);


--
-- Name: __chart__hashtag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__hashtag_id_seq', 1, false);


--
-- Name: __chart__instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__instance_id_seq', 5, true);


--
-- Name: __chart__network_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__network_id_seq', 1, false);


--
-- Name: __chart__notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__notes_id_seq', 6, true);


--
-- Name: __chart__per_user_drive_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__per_user_drive_id_seq', 1, true);


--
-- Name: __chart__per_user_following_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__per_user_following_id_seq', 2, true);


--
-- Name: __chart__per_user_notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__per_user_notes_id_seq', 6, true);


--
-- Name: __chart__per_user_reaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__per_user_reaction_id_seq', 2, true);


--
-- Name: __chart__test_grouped_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__test_grouped_id_seq', 1, false);


--
-- Name: __chart__test_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__test_id_seq', 1, false);


--
-- Name: __chart__test_unique_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__test_unique_id_seq', 1, false);


--
-- Name: __chart__users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart__users_id_seq', 4, true);


--
-- Name: __chart_day__active_users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__active_users_id_seq', 2, true);


--
-- Name: __chart_day__ap_request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__ap_request_id_seq', 2, true);


--
-- Name: __chart_day__drive_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__drive_id_seq', 1, true);


--
-- Name: __chart_day__federation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__federation_id_seq', 4, true);


--
-- Name: __chart_day__hashtag_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__hashtag_id_seq', 1, false);


--
-- Name: __chart_day__instance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__instance_id_seq', 2, true);


--
-- Name: __chart_day__network_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__network_id_seq', 1, false);


--
-- Name: __chart_day__notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__notes_id_seq', 4, true);


--
-- Name: __chart_day__per_user_drive_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__per_user_drive_id_seq', 1, true);


--
-- Name: __chart_day__per_user_following_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__per_user_following_id_seq', 2, true);


--
-- Name: __chart_day__per_user_notes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__per_user_notes_id_seq', 4, true);


--
-- Name: __chart_day__per_user_reaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__per_user_reaction_id_seq', 2, true);


--
-- Name: __chart_day__users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.__chart_day__users_id_seq', 4, true);


--
-- Name: migrations_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.migrations_id_seq', 191, true);


--
-- Name: user_ip_id_seq; Type: SEQUENCE SET; Schema: public; Owner: calckey
--

SELECT pg_catalog.setval('public.user_ip_id_seq', 1, false);


--
-- Name: ad PK_0193d5ef09746e88e9ea92c634d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.ad
    ADD CONSTRAINT "PK_0193d5ef09746e88e9ea92c634d" PRIMARY KEY (id);


--
-- Name: __chart__notes PK_0aec823fa85c7f901bdb3863b14; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__notes
    ADD CONSTRAINT "PK_0aec823fa85c7f901bdb3863b14" PRIMARY KEY (id);


--
-- Name: user_publickey PK_10c146e4b39b443ede016f6736d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_publickey
    ADD CONSTRAINT "PK_10c146e4b39b443ede016f6736d" PRIMARY KEY ("userId");


--
-- Name: user_list_joining PK_11abb3768da1c5f8de101c9df45; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_list_joining
    ADD CONSTRAINT "PK_11abb3768da1c5f8de101c9df45" PRIMARY KEY (id);


--
-- Name: __chart__instance PK_1267c67c7c2d47b4903975f2c00; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__instance
    ADD CONSTRAINT "PK_1267c67c7c2d47b4903975f2c00" PRIMARY KEY (id);


--
-- Name: __chart_day__hashtag PK_13d5a3b089344e5557f8e0980b4; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__hashtag
    ADD CONSTRAINT "PK_13d5a3b089344e5557f8e0980b4" PRIMARY KEY (id);


--
-- Name: user_group_joining PK_15f2425885253c5507e1599cfe7; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_joining
    ADD CONSTRAINT "PK_15f2425885253c5507e1599cfe7" PRIMARY KEY (id);


--
-- Name: user_group_invitation PK_160c63ec02bf23f6a5c5e8140d6; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invitation
    ADD CONSTRAINT "PK_160c63ec02bf23f6a5c5e8140d6" PRIMARY KEY (id);


--
-- Name: note_unread PK_1904eda61a784f57e6e51fa9c1f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_unread
    ADD CONSTRAINT "PK_1904eda61a784f57e6e51fa9c1f" PRIMARY KEY (id);


--
-- Name: auth_session PK_19354ed146424a728c1112a8cbf; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.auth_session
    ADD CONSTRAINT "PK_19354ed146424a728c1112a8cbf" PRIMARY KEY (id);


--
-- Name: __chart_day__per_user_drive PK_1ae135254c137011645da7f4045; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_drive
    ADD CONSTRAINT "PK_1ae135254c137011645da7f4045" PRIMARY KEY (id);


--
-- Name: __chart_day__notes PK_1fa4139e1f338272b758d05e090; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__notes
    ADD CONSTRAINT "PK_1fa4139e1f338272b758d05e090" PRIMARY KEY (id);


--
-- Name: user_ip PK_2c44ddfbf7c0464d028dcef325e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_ip
    ADD CONSTRAINT "PK_2c44ddfbf7c0464d028dcef325e" PRIMARY KEY (id);


--
-- Name: muting PK_2e92d06c8b5c602eeb27ca9ba48; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muting
    ADD CONSTRAINT "PK_2e92d06c8b5c602eeb27ca9ba48" PRIMARY KEY (id);


--
-- Name: __chart__active_users PK_317237a9f733b970604a11e314f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__active_users
    ADD CONSTRAINT "PK_317237a9f733b970604a11e314f" PRIMARY KEY (id);


--
-- Name: __chart__per_user_notes PK_334acf6e915af2f29edc11b8e50; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_notes
    ADD CONSTRAINT "PK_334acf6e915af2f29edc11b8e50" PRIMARY KEY (id);


--
-- Name: user_group_invite PK_3893884af0d3a5f4d01e7921a97; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invite
    ADD CONSTRAINT "PK_3893884af0d3a5f4d01e7921a97" PRIMARY KEY (id);


--
-- Name: user_group PK_3c29fba6fe013ec8724378ce7c9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT "PK_3c29fba6fe013ec8724378ce7c9" PRIMARY KEY (id);


--
-- Name: user_security_key PK_3e508571121ab39c5f85d10c166; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_security_key
    ADD CONSTRAINT "PK_3e508571121ab39c5f85d10c166" PRIMARY KEY (id);


--
-- Name: __chart__test_unique PK_409bac9c97cc612d8500012319d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test_unique
    ADD CONSTRAINT "PK_409bac9c97cc612d8500012319d" PRIMARY KEY (id);


--
-- Name: drive_file PK_43ddaaaf18c9e68029b7cbb032e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_file
    ADD CONSTRAINT "PK_43ddaaaf18c9e68029b7cbb032e" PRIMARY KEY (id);


--
-- Name: channel_note_pining PK_44f7474496bcf2e4b741681146d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_note_pining
    ADD CONSTRAINT "PK_44f7474496bcf2e4b741681146d" PRIMARY KEY (id);


--
-- Name: __chart_day__instance PK_479a8ff9d959274981087043023; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__instance
    ADD CONSTRAINT "PK_479a8ff9d959274981087043023" PRIMARY KEY (id);


--
-- Name: note_watching PK_49286fdb23725945a74aa27d757; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_watching
    ADD CONSTRAINT "PK_49286fdb23725945a74aa27d757" PRIMARY KEY (id);


--
-- Name: announcement_read PK_4b90ad1f42681d97b2683890c5e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.announcement_read
    ADD CONSTRAINT "PK_4b90ad1f42681d97b2683890c5e" PRIMARY KEY (id);


--
-- Name: __chart__users PK_4dfcf2c78d03524b9eb2c99d328; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__users
    ADD CONSTRAINT "PK_4dfcf2c78d03524b9eb2c99d328" PRIMARY KEY (id);


--
-- Name: user_profile PK_51cb79b5555effaf7d69ba1cff9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT "PK_51cb79b5555effaf7d69ba1cff9" PRIMARY KEY ("userId");


--
-- Name: follow_request PK_53a9aa3725f7a3deb150b39dbfc; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.follow_request
    ADD CONSTRAINT "PK_53a9aa3725f7a3deb150b39dbfc" PRIMARY KEY (id);


--
-- Name: __chart__ap_request PK_56a25cd447c7ee08876b3baf8d8; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__ap_request
    ADD CONSTRAINT "PK_56a25cd447c7ee08876b3baf8d8" PRIMARY KEY (id);


--
-- Name: __chart_day__per_user_notes PK_58bab6b6d3ad9310cbc7460fd28; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_notes
    ADD CONSTRAINT "PK_58bab6b6d3ad9310cbc7460fd28" PRIMARY KEY (id);


--
-- Name: channel PK_590f33ee6ee7d76437acf362e39; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel
    ADD CONSTRAINT "PK_590f33ee6ee7d76437acf362e39" PRIMARY KEY (id);


--
-- Name: promo_read PK_61917c1541002422b703318b7c9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_read
    ADD CONSTRAINT "PK_61917c1541002422b703318b7c9" PRIMARY KEY (id);


--
-- Name: registry_item PK_64b3f7e6008b4d89b826cd3af95; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.registry_item
    ADD CONSTRAINT "PK_64b3f7e6008b4d89b826cd3af95" PRIMARY KEY (id);


--
-- Name: __chart_day__per_user_following PK_68ce6b67da57166da66fc8fb27e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_following
    ADD CONSTRAINT "PK_68ce6b67da57166da66fc8fb27e" PRIMARY KEY (id);


--
-- Name: notification PK_705b6c7cdf9b2c2ff7ac7872cb7; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "PK_705b6c7cdf9b2c2ff7ac7872cb7" PRIMARY KEY (id);


--
-- Name: note_edit PK_736fc6e0d4e222ecc6f82058e08; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_edit
    ADD CONSTRAINT "PK_736fc6e0d4e222ecc6f82058e08" PRIMARY KEY (id);


--
-- Name: page PK_742f4117e065c5b6ad21b37ba1f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page
    ADD CONSTRAINT "PK_742f4117e065c5b6ad21b37ba1f" PRIMARY KEY (id);


--
-- Name: note_reaction PK_767ec729b108799b587a3fcc9cf; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_reaction
    ADD CONSTRAINT "PK_767ec729b108799b587a3fcc9cf" PRIMARY KEY (id);


--
-- Name: relay PK_78ebc9cfddf4292633b7ba57aee; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.relay
    ADD CONSTRAINT "PK_78ebc9cfddf4292633b7ba57aee" PRIMARY KEY (id);


--
-- Name: used_username PK_78fd79d2d24c6ac2f4cc9a31a5d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.used_username
    ADD CONSTRAINT "PK_78fd79d2d24c6ac2f4cc9a31a5d" PRIMARY KEY (username);


--
-- Name: drive_folder PK_7a0c089191f5ebdc214e0af808a; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_folder
    ADD CONSTRAINT "PK_7a0c089191f5ebdc214e0af808a" PRIMARY KEY (id);


--
-- Name: __chart_day__federation PK_7ca721c769f31698e0e1331e8e6; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__federation
    ADD CONSTRAINT "PK_7ca721c769f31698e0e1331e8e6" PRIMARY KEY (id);


--
-- Name: page_like PK_813f034843af992d3ae0f43c64c; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page_like
    ADD CONSTRAINT "PK_813f034843af992d3ae0f43c64c" PRIMARY KEY (id);


--
-- Name: gallery_like PK_853ab02be39b8de45cd720cc15f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.gallery_like
    ADD CONSTRAINT "PK_853ab02be39b8de45cd720cc15f" PRIMARY KEY (id);


--
-- Name: __chart__per_user_following PK_85bb1b540363a29c2fec83bd907; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_following
    ADD CONSTRAINT "PK_85bb1b540363a29c2fec83bd907" PRIMARY KEY (id);


--
-- Name: abuse_user_report PK_87873f5f5cc5c321a1306b2d18c; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.abuse_user_report
    ADD CONSTRAINT "PK_87873f5f5cc5c321a1306b2d18c" PRIMARY KEY (id);


--
-- Name: user_list PK_87bab75775fd9b1ff822b656402; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_list
    ADD CONSTRAINT "PK_87bab75775fd9b1ff822b656402" PRIMARY KEY (id);


--
-- Name: muted_note PK_897e2eff1c0b9b64e55ca1418a4; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muted_note
    ADD CONSTRAINT "PK_897e2eff1c0b9b64e55ca1418a4" PRIMARY KEY (id);


--
-- Name: __chart_day__per_user_reaction PK_8af24e2d51ff781a354fe595eda; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_reaction
    ADD CONSTRAINT "PK_8af24e2d51ff781a354fe595eda" PRIMARY KEY (id);


--
-- Name: channel_following PK_8b104be7f7415113f2a02cd5bdd; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_following
    ADD CONSTRAINT "PK_8b104be7f7415113f2a02cd5bdd" PRIMARY KEY (id);


--
-- Name: migrations PK_8c82d7f526340ab734260ea46be; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.migrations
    ADD CONSTRAINT "PK_8c82d7f526340ab734260ea46be" PRIMARY KEY (id);


--
-- Name: gallery_post PK_8e90d7b6015f2c4518881b14753; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.gallery_post
    ADD CONSTRAINT "PK_8e90d7b6015f2c4518881b14753" PRIMARY KEY (id);


--
-- Name: __chart_day__ap_request PK_9318b49daee320194e23f712e69; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__ap_request
    ADD CONSTRAINT "PK_9318b49daee320194e23f712e69" PRIMARY KEY (id);


--
-- Name: app PK_9478629fc093d229df09e560aea; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.app
    ADD CONSTRAINT "PK_9478629fc093d229df09e560aea" PRIMARY KEY (id);


--
-- Name: note PK_96d0c172a4fba276b1bbed43058; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT "PK_96d0c172a4fba276b1bbed43058" PRIMARY KEY (id);


--
-- Name: __chart__per_user_reaction PK_984f54dae441e65b633e8d27a7f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_reaction
    ADD CONSTRAINT "PK_984f54dae441e65b633e8d27a7f" PRIMARY KEY (id);


--
-- Name: signin PK_9e96ddc025712616fc492b3b588; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.signin
    ADD CONSTRAINT "PK_9e96ddc025712616fc492b3b588" PRIMARY KEY (id);


--
-- Name: user_note_pining PK_a6a2dad4ae000abce2ea9d9b103; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_note_pining
    ADD CONSTRAINT "PK_a6a2dad4ae000abce2ea9d9b103" PRIMARY KEY (id);


--
-- Name: note_favorite PK_af0da35a60b9fa4463a62082b36; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_favorite
    ADD CONSTRAINT "PK_af0da35a60b9fa4463a62082b36" PRIMARY KEY (id);


--
-- Name: __chart_day__active_users PK_b1790489b14f005ae8f404f5795; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__active_users
    ADD CONSTRAINT "PK_b1790489b14f005ae8f404f5795" PRIMARY KEY (id);


--
-- Name: __chart__federation PK_b39dcd31a0fe1a7757e348e85fd; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__federation
    ADD CONSTRAINT "PK_b39dcd31a0fe1a7757e348e85fd" PRIMARY KEY (id);


--
-- Name: __chart__test PK_b4bc31dffbd1b785276a3ecfc1e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test
    ADD CONSTRAINT "PK_b4bc31dffbd1b785276a3ecfc1e" PRIMARY KEY (id);


--
-- Name: __chart__network PK_bc4290c2e27fad14ef0c1ca93f3; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__network
    ADD CONSTRAINT "PK_bc4290c2e27fad14ef0c1ca93f3" PRIMARY KEY (id);


--
-- Name: antenna PK_c170b99775e1dccca947c9f2d5f; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.antenna
    ADD CONSTRAINT "PK_c170b99775e1dccca947c9f2d5f" PRIMARY KEY (id);


--
-- Name: __chart__hashtag PK_c32f1ea2b44a5d2f7881e37f8f9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__hashtag
    ADD CONSTRAINT "PK_c32f1ea2b44a5d2f7881e37f8f9" PRIMARY KEY (id);


--
-- Name: meta PK_c4c17a6c2bd7651338b60fc590b; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.meta
    ADD CONSTRAINT "PK_c4c17a6c2bd7651338b60fc590b" PRIMARY KEY (id);


--
-- Name: following PK_c76c6e044bdf76ecf8bfb82a645; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.following
    ADD CONSTRAINT "PK_c76c6e044bdf76ecf8bfb82a645" PRIMARY KEY (id);


--
-- Name: __chart_day__network PK_cac499d6f471042dfed1e7e0132; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__network
    ADD CONSTRAINT "PK_cac499d6f471042dfed1e7e0132" PRIMARY KEY (id);


--
-- Name: user PK_cace4a159ff9f2512dd42373760; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "PK_cace4a159ff9f2512dd42373760" PRIMARY KEY (id);


--
-- Name: hashtag PK_cb36eb8af8412bfa978f1165d78; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.hashtag
    ADD CONSTRAINT "PK_cb36eb8af8412bfa978f1165d78" PRIMARY KEY (id);


--
-- Name: moderation_log PK_d0adca6ecfd068db83e4526cc26; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.moderation_log
    ADD CONSTRAINT "PK_d0adca6ecfd068db83e4526cc26" PRIMARY KEY (id);


--
-- Name: attestation_challenge PK_d0ba6786e093f1bcb497572a6b5; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.attestation_challenge
    ADD CONSTRAINT "PK_d0ba6786e093f1bcb497572a6b5" PRIMARY KEY (id, "userId");


--
-- Name: __chart__per_user_drive PK_d0ef23d24d666e1a44a0cd3d208; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_drive
    ADD CONSTRAINT "PK_d0ef23d24d666e1a44a0cd3d208" PRIMARY KEY (id);


--
-- Name: user_pending PK_d4c84e013c98ec02d19b8fbbafa; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_pending
    ADD CONSTRAINT "PK_d4c84e013c98ec02d19b8fbbafa" PRIMARY KEY (id);


--
-- Name: __chart_day__users PK_d7f7185abb9851f70c4726c54bd; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__users
    ADD CONSTRAINT "PK_d7f7185abb9851f70c4726c54bd" PRIMARY KEY (id);


--
-- Name: poll PK_da851e06d0dfe2ef397d8b1bf1b; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll
    ADD CONSTRAINT "PK_da851e06d0dfe2ef397d8b1bf1b" PRIMARY KEY ("noteId");


--
-- Name: messaging_message PK_db398fd79dc95d0eb8c30456eaa; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.messaging_message
    ADD CONSTRAINT "PK_db398fd79dc95d0eb8c30456eaa" PRIMARY KEY (id);


--
-- Name: emoji PK_df74ce05e24999ee01ea0bc50a3; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.emoji
    ADD CONSTRAINT "PK_df74ce05e24999ee01ea0bc50a3" PRIMARY KEY (id);


--
-- Name: announcement PK_e0ef0550174fd1099a308fd18a0; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.announcement
    ADD CONSTRAINT "PK_e0ef0550174fd1099a308fd18a0" PRIMARY KEY (id);


--
-- Name: promo_note PK_e263909ca4fe5d57f8d4230dd5c; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_note
    ADD CONSTRAINT "PK_e263909ca4fe5d57f8d4230dd5c" PRIMARY KEY ("noteId");


--
-- Name: blocking PK_e5d9a541cc1965ee7e048ea09dd; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.blocking
    ADD CONSTRAINT "PK_e5d9a541cc1965ee7e048ea09dd" PRIMARY KEY (id);


--
-- Name: webhook PK_e6765510c2d078db49632b59020; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.webhook
    ADD CONSTRAINT "PK_e6765510c2d078db49632b59020" PRIMARY KEY (id);


--
-- Name: __chart_day__drive PK_e7ec0de057c77c40fc8d8b62151; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__drive
    ADD CONSTRAINT "PK_e7ec0de057c77c40fc8d8b62151" PRIMARY KEY (id);


--
-- Name: sw_subscription PK_e8f763631530051b95eb6279b91; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.sw_subscription
    ADD CONSTRAINT "PK_e8f763631530051b95eb6279b91" PRIMARY KEY (id);


--
-- Name: clip_note PK_e94cda2f40a99b57e032a1a738b; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.clip_note
    ADD CONSTRAINT "PK_e94cda2f40a99b57e032a1a738b" PRIMARY KEY (id);


--
-- Name: instance PK_eaf60e4a0c399c9935413e06474; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.instance
    ADD CONSTRAINT "PK_eaf60e4a0c399c9935413e06474" PRIMARY KEY (id);


--
-- Name: note_thread_muting PK_ec5936d94d1a0369646d12a3a47; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_thread_muting
    ADD CONSTRAINT "PK_ec5936d94d1a0369646d12a3a47" PRIMARY KEY (id);


--
-- Name: clip PK_f0685dac8d4dd056d7255670b75; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.clip
    ADD CONSTRAINT "PK_f0685dac8d4dd056d7255670b75" PRIMARY KEY (id);


--
-- Name: registration_ticket PK_f11696b6fafcf3662d4292734f8; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.registration_ticket
    ADD CONSTRAINT "PK_f11696b6fafcf3662d4292734f8" PRIMARY KEY (id);


--
-- Name: access_token PK_f20f028607b2603deabd8182d12; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.access_token
    ADD CONSTRAINT "PK_f20f028607b2603deabd8182d12" PRIMARY KEY (id);


--
-- Name: user_keypair PK_f4853eb41ab722fe05f81cedeb6; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_keypair
    ADD CONSTRAINT "PK_f4853eb41ab722fe05f81cedeb6" PRIMARY KEY ("userId");


--
-- Name: __chart__test_grouped PK_f4a2b175d308695af30d4293272; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__test_grouped
    ADD CONSTRAINT "PK_f4a2b175d308695af30d4293272" PRIMARY KEY (id);


--
-- Name: __chart__drive PK_f96bc548a765cd4b3b354221ce7; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__drive
    ADD CONSTRAINT "PK_f96bc548a765cd4b3b354221ce7" PRIMARY KEY (id);


--
-- Name: password_reset_request PK_fcf4b02eae1403a2edaf87fd074; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.password_reset_request
    ADD CONSTRAINT "PK_fcf4b02eae1403a2edaf87fd074" PRIMARY KEY (id);


--
-- Name: poll_vote PK_fd002d371201c472490ba89c6a0; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll_vote
    ADD CONSTRAINT "PK_fd002d371201c472490ba89c6a0" PRIMARY KEY (id);


--
-- Name: renote_muting PK_renoteMuting_id; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.renote_muting
    ADD CONSTRAINT "PK_renoteMuting_id" PRIMARY KEY (id);


--
-- Name: user REL_58f5c71eaab331645112cf8cfa; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "REL_58f5c71eaab331645112cf8cfa" UNIQUE ("avatarId");


--
-- Name: user REL_afc64b53f8db3707ceb34eb28e; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "REL_afc64b53f8db3707ceb34eb28e" UNIQUE ("bannerId");


--
-- Name: __chart__active_users UQ_0ad37b7ef50f4ddc84363d7ccca; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__active_users
    ADD CONSTRAINT "UQ_0ad37b7ef50f4ddc84363d7ccca" UNIQUE (date);


--
-- Name: __chart_day__drive UQ_0b60ebb3aa0065f10b0616c1171; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__drive
    ADD CONSTRAINT "UQ_0b60ebb3aa0065f10b0616c1171" UNIQUE (date);


--
-- Name: user_publickey UQ_10c146e4b39b443ede016f6736d; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_publickey
    ADD CONSTRAINT "UQ_10c146e4b39b443ede016f6736d" UNIQUE ("userId");


--
-- Name: __chart__drive UQ_13565815f618a1ff53886c5b28a; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__drive
    ADD CONSTRAINT "UQ_13565815f618a1ff53886c5b28a" UNIQUE (date);


--
-- Name: __chart_day__notes UQ_1a527b423ad0858a1af5a056d43; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__notes
    ADD CONSTRAINT "UQ_1a527b423ad0858a1af5a056d43" UNIQUE (date);


--
-- Name: __chart__per_user_reaction UQ_229a41ad465f9205f1f57032910; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_reaction
    ADD CONSTRAINT "UQ_229a41ad465f9205f1f57032910" UNIQUE (date, "group");


--
-- Name: __chart__hashtag UQ_25a97c02003338124b2b75fdbc8; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__hashtag
    ADD CONSTRAINT "UQ_25a97c02003338124b2b75fdbc8" UNIQUE (date, "group");


--
-- Name: __chart__per_user_drive UQ_30bf67687f483ace115c5ca6429; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_drive
    ADD CONSTRAINT "UQ_30bf67687f483ace115c5ca6429" UNIQUE (date, "group");


--
-- Name: __chart__federation UQ_36cb699c49580d4e6c2e6159f97; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__federation
    ADD CONSTRAINT "UQ_36cb699c49580d4e6c2e6159f97" UNIQUE (date);


--
-- Name: __chart__instance UQ_39ee857ab2f23493037c6b66311; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__instance
    ADD CONSTRAINT "UQ_39ee857ab2f23493037c6b66311" UNIQUE (date, "group");


--
-- Name: __chart__notes UQ_42eb716a37d381cdf566192b2be; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__notes
    ADD CONSTRAINT "UQ_42eb716a37d381cdf566192b2be" UNIQUE (date);


--
-- Name: __chart__per_user_notes UQ_5048e9daccbbbc6d567bb142d34; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_notes
    ADD CONSTRAINT "UQ_5048e9daccbbbc6d567bb142d34" UNIQUE (date, "group");


--
-- Name: user_profile UQ_51cb79b5555effaf7d69ba1cff9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT "UQ_51cb79b5555effaf7d69ba1cff9" UNIQUE ("userId");


--
-- Name: __chart_day__federation UQ_617a8fe225a6e701d89e02d2c74; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__federation
    ADD CONSTRAINT "UQ_617a8fe225a6e701d89e02d2c74" UNIQUE (date);


--
-- Name: __chart_day__per_user_drive UQ_62aa5047b5aec92524f24c701d7; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_drive
    ADD CONSTRAINT "UQ_62aa5047b5aec92524f24c701d7" UNIQUE (date, "group");


--
-- Name: user_profile UQ_6dc44f1ceb65b1e72bacef2ca27; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT "UQ_6dc44f1ceb65b1e72bacef2ca27" UNIQUE ("pinnedPageId");


--
-- Name: __chart__users UQ_845254b3eaf708ae8a6cac30265; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__users
    ADD CONSTRAINT "UQ_845254b3eaf708ae8a6cac30265" UNIQUE (date);


--
-- Name: __chart_day__network UQ_8bfa548c2b31f9e07db113773ee; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__network
    ADD CONSTRAINT "UQ_8bfa548c2b31f9e07db113773ee" UNIQUE (date);


--
-- Name: __chart_day__hashtag UQ_8f589cf056ff51f09d6096f6450; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__hashtag
    ADD CONSTRAINT "UQ_8f589cf056ff51f09d6096f6450" UNIQUE (date, "group");


--
-- Name: __chart__network UQ_a1efd3e0048a5f2793a47360dc6; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__network
    ADD CONSTRAINT "UQ_a1efd3e0048a5f2793a47360dc6" UNIQUE (date);


--
-- Name: __chart_day__ap_request UQ_a848f66d6cec11980a5dd595822; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__ap_request
    ADD CONSTRAINT "UQ_a848f66d6cec11980a5dd595822" UNIQUE (date);


--
-- Name: user UQ_a854e557b1b14814750c7c7b0c9; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "UQ_a854e557b1b14814750c7c7b0c9" UNIQUE (token);


--
-- Name: __chart__per_user_following UQ_b77d4dd9562c3a899d9a286fcd7; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__per_user_following
    ADD CONSTRAINT "UQ_b77d4dd9562c3a899d9a286fcd7" UNIQUE (date, "group");


--
-- Name: __chart_day__per_user_notes UQ_c5545d4b31cdc684034e33b81c3; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_notes
    ADD CONSTRAINT "UQ_c5545d4b31cdc684034e33b81c3" UNIQUE (date, "group");


--
-- Name: __chart_day__users UQ_cad6e07c20037f31cdba8a350c3; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__users
    ADD CONSTRAINT "UQ_cad6e07c20037f31cdba8a350c3" UNIQUE (date);


--
-- Name: __chart_day__per_user_reaction UQ_d54b653660d808b118e36c184c0; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_reaction
    ADD CONSTRAINT "UQ_d54b653660d808b118e36c184c0" UNIQUE (date, "group");


--
-- Name: __chart_day__active_users UQ_d5954f3df5e5e3bdfc3c03f3906; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__active_users
    ADD CONSTRAINT "UQ_d5954f3df5e5e3bdfc3c03f3906" UNIQUE (date);


--
-- Name: poll UQ_da851e06d0dfe2ef397d8b1bf1b; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll
    ADD CONSTRAINT "UQ_da851e06d0dfe2ef397d8b1bf1b" UNIQUE ("noteId");


--
-- Name: promo_note UQ_e263909ca4fe5d57f8d4230dd5c; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_note
    ADD CONSTRAINT "UQ_e263909ca4fe5d57f8d4230dd5c" UNIQUE ("noteId");


--
-- Name: __chart_day__per_user_following UQ_e4849a3231f38281280ea4c0eee; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__per_user_following
    ADD CONSTRAINT "UQ_e4849a3231f38281280ea4c0eee" UNIQUE (date, "group");


--
-- Name: __chart__ap_request UQ_e56f4beac5746d44bc3e19c80d0; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart__ap_request
    ADD CONSTRAINT "UQ_e56f4beac5746d44bc3e19c80d0" UNIQUE (date);


--
-- Name: user_keypair UQ_f4853eb41ab722fe05f81cedeb6; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_keypair
    ADD CONSTRAINT "UQ_f4853eb41ab722fe05f81cedeb6" UNIQUE ("userId");


--
-- Name: __chart_day__instance UQ_fea7c0278325a1a2492f2d6acbf; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.__chart_day__instance
    ADD CONSTRAINT "UQ_fea7c0278325a1a2492f2d6acbf" UNIQUE (date, "group");


--
-- Name: seaql_migrations seaql_migrations_pkey; Type: CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.seaql_migrations
    ADD CONSTRAINT seaql_migrations_pkey PRIMARY KEY (version);


--
-- Name: IDX_00ceffb0cdc238b3233294f08f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_00ceffb0cdc238b3233294f08f" ON public.drive_folder USING btree ("parentId");


--
-- Name: IDX_01f4581f114e0ebd2bbb876f0b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_01f4581f114e0ebd2bbb876f0b" ON public.note_reaction USING btree ("createdAt");


--
-- Name: IDX_02878d441ceae15ce060b73daf; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_02878d441ceae15ce060b73daf" ON public.drive_folder USING btree ("createdAt");


--
-- Name: IDX_03e7028ab8388a3f5e3ce2a861; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_03e7028ab8388a3f5e3ce2a861" ON public.note_watching USING btree ("noteId");


--
-- Name: IDX_048a757923ed8b157e9895da53; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_048a757923ed8b157e9895da53" ON public.app USING btree ("createdAt");


--
-- Name: IDX_04cc96756f89d0b7f9473e8cdf; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_04cc96756f89d0b7f9473e8cdf" ON public.abuse_user_report USING btree ("reporterId");


--
-- Name: IDX_05cca34b985d1b8edc1d1e28df; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_05cca34b985d1b8edc1d1e28df" ON public.gallery_post USING btree (tags);


--
-- Name: IDX_0610ebcfcfb4a18441a9bcdab2; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0610ebcfcfb4a18441a9bcdab2" ON public.poll USING btree ("userId");


--
-- Name: IDX_0627125f1a8a42c9a1929edb55; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0627125f1a8a42c9a1929edb55" ON public.blocking USING btree ("blockerId");


--
-- Name: IDX_080ab397c379af09b9d2169e5b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_080ab397c379af09b9d2169e5b" ON public.notification USING btree ("isRead");


--
-- Name: IDX_094b86cd36bb805d1aa1e8cc9a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_094b86cd36bb805d1aa1e8cc9a" ON public.channel USING btree ("usersCount");


--
-- Name: IDX_0a72bdfcdb97c0eca11fe7ecad; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0a72bdfcdb97c0eca11fe7ecad" ON public.registry_item USING btree (domain);


--
-- Name: IDX_0ad37b7ef50f4ddc84363d7ccc; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0ad37b7ef50f4ddc84363d7ccc" ON public.__chart__active_users USING btree (date);


--
-- Name: IDX_0b03cbcd7e6a7ce068efa8ecc2; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0b03cbcd7e6a7ce068efa8ecc2" ON public.hashtag USING btree ("attachedRemoteUsersCount");


--
-- Name: IDX_0b575fa9a4cfe638a925949285; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0b575fa9a4cfe638a925949285" ON public.password_reset_request USING btree (token);


--
-- Name: IDX_0b60ebb3aa0065f10b0616c117; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0b60ebb3aa0065f10b0616c117" ON public.__chart_day__drive USING btree (date);


--
-- Name: IDX_0c44bf4f680964145f2a68a341; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0c44bf4f680964145f2a68a341" ON public.hashtag USING btree ("attachedLocalUsersCount");


--
-- Name: IDX_0d7718e562dcedd0aa5cf2c9f7; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0d7718e562dcedd0aa5cf2c9f7" ON public.user_security_key USING btree ("publicKey");


--
-- Name: IDX_0d9a1738f2cf7f3b1c3334dfab; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0d9a1738f2cf7f3b1c3334dfab" ON public.relay USING btree (inbox);


--
-- Name: IDX_0e206cec573f1edff4a3062923; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0e206cec573f1edff4a3062923" ON public.hashtag USING btree ("mentionedLocalUsersCount");


--
-- Name: IDX_0e43068c3f92cab197c3d3cd86; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0e43068c3f92cab197c3d3cd86" ON public.channel_following USING btree ("followeeId");


--
-- Name: IDX_0e61efab7f88dbb79c9166dbb4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0e61efab7f88dbb79c9166dbb4" ON public.page_like USING btree ("userId");


--
-- Name: IDX_0f4fb9ad355f3effff221ef245; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0f4fb9ad355f3effff221ef245" ON public.note_favorite USING btree ("userId", "noteId");


--
-- Name: IDX_0f58c11241e649d2a638a8de94; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0f58c11241e649d2a638a8de94" ON public.channel USING btree ("notesCount");


--
-- Name: IDX_0fb627e1c2f753262a74f0562d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_0fb627e1c2f753262a74f0562d" ON public.poll_vote USING btree ("createdAt");


--
-- Name: IDX_0ff69e8dfa9fe31bb4a4660f59; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_0ff69e8dfa9fe31bb4a4660f59" ON public.registration_ticket USING btree (code);


--
-- Name: IDX_1039988afa3bf991185b277fe0; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_1039988afa3bf991185b277fe0" ON public.user_group_invite USING btree ("userId");


--
-- Name: IDX_1129c2ef687fc272df040bafaa; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_1129c2ef687fc272df040bafaa" ON public.ad USING btree ("createdAt");


--
-- Name: IDX_118ec703e596086fc4515acb39; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_118ec703e596086fc4515acb39" ON public.announcement USING btree ("createdAt");


--
-- Name: IDX_11e71f2511589dcc8a4d3214f9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_11e71f2511589dcc8a4d3214f9" ON public.channel_following USING btree ("createdAt");


--
-- Name: IDX_12c01c0d1a79f77d9f6c15fadd; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_12c01c0d1a79f77d9f6c15fadd" ON public.follow_request USING btree ("followeeId");


--
-- Name: IDX_13565815f618a1ff53886c5b28; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_13565815f618a1ff53886c5b28" ON public.__chart__drive USING btree (date);


--
-- Name: IDX_13761f64257f40c5636d0ff95e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_13761f64257f40c5636d0ff95e" ON public.note_reaction USING btree ("userId");


--
-- Name: IDX_153536c67d05e9adb24e99fc2b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_153536c67d05e9adb24e99fc2b" ON public.note USING btree (uri);


--
-- Name: IDX_16effb2e888f6763673b579f80; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_16effb2e888f6763673b579f80" ON public.__chart__test_unique USING btree (date) WHERE ("group" IS NULL);


--
-- Name: IDX_171e64971c780ebd23fae140bb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_171e64971c780ebd23fae140bb" ON public.user_publickey USING btree ("keyId");


--
-- Name: IDX_17cb3553c700a4985dff5a30ff; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_17cb3553c700a4985dff5a30ff" ON public.note USING btree ("replyId");


--
-- Name: IDX_1a165c68a49d08f11caffbd206; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_1a165c68a49d08f11caffbd206" ON public.gallery_post USING btree ("likedCount");


--
-- Name: IDX_1a527b423ad0858a1af5a056d4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_1a527b423ad0858a1af5a056d4" ON public.__chart_day__notes USING btree (date);


--
-- Name: IDX_1eb9d9824a630321a29fd3b290; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_1eb9d9824a630321a29fd3b290" ON public.muting USING btree ("muterId", "muteeId");


--
-- Name: IDX_20e30aa35180e317e133d75316; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_20e30aa35180e317e133d75316" ON public.user_group USING btree ("createdAt");


--
-- Name: IDX_2133ef8317e4bdb839c0dcbf13; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_2133ef8317e4bdb839c0dcbf13" ON public.page USING btree ("userId", name);


--
-- Name: IDX_229a41ad465f9205f1f5703291; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_229a41ad465f9205f1f5703291" ON public.__chart__per_user_reaction USING btree (date, "group");


--
-- Name: IDX_22baca135bb8a3ea1a83d13df3; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_22baca135bb8a3ea1a83d13df3" ON public.registry_item USING btree (scope);


--
-- Name: IDX_24e0042143a18157b234df186c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_24e0042143a18157b234df186c" ON public.following USING btree ("followeeId");


--
-- Name: IDX_25a97c02003338124b2b75fdbc; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_25a97c02003338124b2b75fdbc" ON public.__chart__hashtag USING btree (date, "group");


--
-- Name: IDX_25b1dd384bec391b07b74b861c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_25b1dd384bec391b07b74b861c" ON public.note_unread USING btree ("isMentioned");


--
-- Name: IDX_25dfc71b0369b003a4cd434d0b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_25dfc71b0369b003a4cd434d0b" ON public.note USING btree ("attachedFileTypes");


--
-- Name: IDX_2710a55f826ee236ea1a62698f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2710a55f826ee236ea1a62698f" ON public.hashtag USING btree ("mentionedUsersCount");


--
-- Name: IDX_2882b8a1a07c7d281a98b6db16; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_2882b8a1a07c7d281a98b6db16" ON public.promo_read USING btree ("userId", "noteId");


--
-- Name: IDX_29c11c7deb06615076f8c95b80; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_29c11c7deb06615076f8c95b80" ON public.note_thread_muting USING btree ("userId");


--
-- Name: IDX_29e8c1d579af54d4232939f994; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_29e8c1d579af54d4232939f994" ON public.note_unread USING btree ("noteUserId");


--
-- Name: IDX_29ef80c6f13bcea998447fce43; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_29ef80c6f13bcea998447fce43" ON public.channel USING btree ("lastNotedAt");


--
-- Name: IDX_2b15aaf4a0dc5be3499af7ab6a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2b15aaf4a0dc5be3499af7ab6a" ON public.abuse_user_report USING btree (resolved);


--
-- Name: IDX_2b5ec6c574d6802c94c80313fb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2b5ec6c574d6802c94c80313fb" ON public.clip USING btree ("userId");


--
-- Name: IDX_2c308dbdc50d94dc625670055f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2c308dbdc50d94dc625670055f" ON public.signin USING btree ("userId");


--
-- Name: IDX_2c4be03b446884f9e9c502135b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2c4be03b446884f9e9c502135b" ON public.messaging_message USING btree ("groupId");


--
-- Name: IDX_2cd3b2a6b4cf0b910b260afe08; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2cd3b2a6b4cf0b910b260afe08" ON public.instance USING btree ("caughtAt");


--
-- Name: IDX_2cd4a2743a99671308f5417759; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2cd4a2743a99671308f5417759" ON public.blocking USING btree ("blockeeId");


--
-- Name: IDX_2da24ce20ad209f1d9dc032457; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_2da24ce20ad209f1d9dc032457" ON public.ad USING btree ("expiresAt");


--
-- Name: IDX_2e230dd45a10e671d781d99f3e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_2e230dd45a10e671d781d99f3e" ON public.channel_following USING btree ("followerId", "followeeId");


--
-- Name: IDX_307be5f1d1252e0388662acb96; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_307be5f1d1252e0388662acb96" ON public.following USING btree ("followerId", "followeeId");


--
-- Name: IDX_30bf67687f483ace115c5ca642; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_30bf67687f483ace115c5ca642" ON public.__chart__per_user_drive USING btree (date, "group");


--
-- Name: IDX_315c779174fe8247ab324f036e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_315c779174fe8247ab324f036e" ON public.drive_file USING btree ("isLink");


--
-- Name: IDX_318cdf42a9cfc11f479bd802bb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_318cdf42a9cfc11f479bd802bb" ON public.note_watching USING btree ("createdAt");


--
-- Name: IDX_3252a5df8d5bbd16b281f7799e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3252a5df8d5bbd16b281f7799e" ON public."user" USING btree (host);


--
-- Name: IDX_33f33cc8ef29d805a97ff4628b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_33f33cc8ef29d805a97ff4628b" ON public.notification USING btree (type);


--
-- Name: IDX_34500da2e38ac393f7bb6b299c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_34500da2e38ac393f7bb6b299c" ON public.instance USING btree ("isSuspended");


--
-- Name: IDX_347fec870eafea7b26c8a73bac; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_347fec870eafea7b26c8a73bac" ON public.hashtag USING btree (name);


--
-- Name: IDX_361b500e06721013c124b7b6c5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_361b500e06721013c124b7b6c5" ON public.user_ip USING btree ("userId", ip);


--
-- Name: IDX_36cb699c49580d4e6c2e6159f9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_36cb699c49580d4e6c2e6159f9" ON public.__chart__federation USING btree (date);


--
-- Name: IDX_37bb9a1b4585f8a3beb24c62d6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_37bb9a1b4585f8a3beb24c62d6" ON public.drive_file USING btree (md5);


--
-- Name: IDX_39ee857ab2f23493037c6b6631; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_39ee857ab2f23493037c6b6631" ON public.__chart__instance USING btree (date, "group");


--
-- Name: IDX_3b33dff77bb64b23c88151d23e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3b33dff77bb64b23c88151d23e" ON public.drive_file USING btree ("maybeSensitive");


--
-- Name: IDX_3b4e96eec8d36a8bbb9d02aa71; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3b4e96eec8d36a8bbb9d02aa71" ON public.notification USING btree ("notifierId");


--
-- Name: IDX_3befe6f999c86aff06eb0257b4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3befe6f999c86aff06eb0257b4" ON public.user_profile USING btree ("enableWordMute");


--
-- Name: IDX_3c601b70a1066d2c8b517094cb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3c601b70a1066d2c8b517094cb" ON public.notification USING btree ("notifieeId");


--
-- Name: IDX_3ca50563facd913c425e7a89ee; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3ca50563facd913c425e7a89ee" ON public.gallery_post USING btree ("fileIds");


--
-- Name: IDX_3d6b372788ab01be58853003c9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3d6b372788ab01be58853003c9" ON public.user_group USING btree ("userId");


--
-- Name: IDX_3f5b0899ef90527a3462d7c2cb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_3f5b0899ef90527a3462d7c2cb" ON public.app USING btree ("userId");


--
-- Name: IDX_410cd649884b501c02d6e72738; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_410cd649884b501c02d6e72738" ON public.user_note_pining USING btree ("userId", "noteId");


--
-- Name: IDX_42eb716a37d381cdf566192b2b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_42eb716a37d381cdf566192b2b" ON public.__chart__notes USING btree (date);


--
-- Name: IDX_44499765eec6b5489d72c4253b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_44499765eec6b5489d72c4253b" ON public.note_watching USING btree ("noteUserId");


--
-- Name: IDX_45145e4953780f3cd5656f0ea6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_45145e4953780f3cd5656f0ea6" ON public.note_reaction USING btree ("noteId");


--
-- Name: IDX_47efb914aed1f72dd39a306c7b; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_47efb914aed1f72dd39a306c7b" ON public.attestation_challenge USING btree (challenge);


--
-- Name: IDX_47f4b1892f5d6ba8efb3057d81; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_47f4b1892f5d6ba8efb3057d81" ON public.note_favorite USING btree ("userId");


--
-- Name: IDX_4bb7fd4a34492ae0e6cc8d30ac; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_4bb7fd4a34492ae0e6cc8d30ac" ON public.password_reset_request USING btree ("userId");


--
-- Name: IDX_4c02d38a976c3ae132228c6fce; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_4c02d38a976c3ae132228c6fce" ON public.hashtag USING btree ("mentionedRemoteUsersCount");


--
-- Name: IDX_4ccd2239268ebbd1b35e318754; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_4ccd2239268ebbd1b35e318754" ON public.following USING btree ("followerHost");


--
-- Name: IDX_4ce6fb9c70529b4c8ac46c9bfa; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_4ce6fb9c70529b4c8ac46c9bfa" ON public.page_like USING btree ("userId", "pageId");


--
-- Name: IDX_4e5c4c99175638ec0761714ab0; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_4e5c4c99175638ec0761714ab0" ON public.user_pending USING btree (code);


--
-- Name: IDX_4ebbf7f93cdc10e8d1ef2fc6cd; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_4ebbf7f93cdc10e8d1ef2fc6cd" ON public.abuse_user_report USING btree ("targetUserHost");


--
-- Name: IDX_4f4d35e1256c84ae3d1f0eab10; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_4f4d35e1256c84ae3d1f0eab10" ON public.emoji USING btree (name, host);


--
-- Name: IDX_5048e9daccbbbc6d567bb142d3; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_5048e9daccbbbc6d567bb142d3" ON public.__chart__per_user_notes USING btree (date, "group");


--
-- Name: IDX_50bd7164c5b78f1f4a42c4d21f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_50bd7164c5b78f1f4a42c4d21f" ON public.poll_vote USING btree ("userId", "noteId", choice);


--
-- Name: IDX_51c063b6a133a9cb87145450f5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_51c063b6a133a9cb87145450f5" ON public.note USING btree ("fileIds");


--
-- Name: IDX_52ccc804d7c69037d558bac4c9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_52ccc804d7c69037d558bac4c9" ON public.note USING btree ("renoteId");


--
-- Name: IDX_5377c307783fce2b6d352e1203; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_5377c307783fce2b6d352e1203" ON public.messaging_message USING btree ("userId");


--
-- Name: IDX_54ebcb6d27222913b908d56fd8; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_54ebcb6d27222913b908d56fd8" ON public.note USING btree (mentions);


--
-- Name: IDX_55720b33a61a7c806a8215b825; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_55720b33a61a7c806a8215b825" ON public.drive_file USING btree ("userId", "folderId", id);


--
-- Name: IDX_56b0166d34ddae49d8ef7610bb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_56b0166d34ddae49d8ef7610bb" ON public.note_unread USING btree ("userId");


--
-- Name: IDX_582f8fab771a9040a12961f3e7; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_582f8fab771a9040a12961f3e7" ON public.following USING btree ("createdAt");


--
-- Name: IDX_5900e907bb46516ddf2871327c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_5900e907bb46516ddf2871327c" ON public.emoji USING btree (host);


--
-- Name: IDX_5a056076f76b2efe08216ba655; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_5a056076f76b2efe08216ba655" ON public.webhook USING btree (active);


--
-- Name: IDX_5b87d9d19127bd5d92026017a7; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_5b87d9d19127bd5d92026017a7" ON public.note USING btree ("userId");


--
-- Name: IDX_5cc8c468090e129857e9fecce5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_5cc8c468090e129857e9fecce5" ON public.user_group_invitation USING btree ("userGroupId");


--
-- Name: IDX_5deb01ae162d1d70b80d064c27; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_5deb01ae162d1d70b80d064c27" ON public."user" USING btree ("usernameLower", host);


--
-- Name: IDX_603a7b1e7aa0533c6c88e9bfaf; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_603a7b1e7aa0533c6c88e9bfaf" ON public.announcement_read USING btree ("announcementId");


--
-- Name: IDX_605472305f26818cc93d1baaa7; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_605472305f26818cc93d1baaa7" ON public.user_list_joining USING btree ("userListId");


--
-- Name: IDX_617a8fe225a6e701d89e02d2c7; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_617a8fe225a6e701d89e02d2c7" ON public.__chart_day__federation USING btree (date);


--
-- Name: IDX_62aa5047b5aec92524f24c701d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_62aa5047b5aec92524f24c701d" ON public.__chart_day__per_user_drive USING btree (date, "group");


--
-- Name: IDX_62cb09e1129f6ec024ef66e183; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_62cb09e1129f6ec024ef66e183" ON public.auth_session USING btree (token);


--
-- Name: IDX_636e977ff90b23676fb5624b25; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_636e977ff90b23676fb5624b25" ON public.muted_note USING btree (reason);


--
-- Name: IDX_6446c571a0e8d0f05f01c78909; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_6446c571a0e8d0f05f01c78909" ON public.antenna USING btree ("userId");


--
-- Name: IDX_64c327441248bae40f7d92f34f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_64c327441248bae40f7d92f34f" ON public.access_token USING btree (hash);


--
-- Name: IDX_6516c5a6f3c015b4eed39978be; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_6516c5a6f3c015b4eed39978be" ON public.following USING btree ("followerId");


--
-- Name: IDX_66d2bd2ee31d14bcc23069a89f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_66d2bd2ee31d14bcc23069a89f" ON public.poll_vote USING btree ("userId");


--
-- Name: IDX_67dc758bc0566985d1b3d39986; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_67dc758bc0566985d1b3d39986" ON public.user_group_joining USING btree ("userGroupId");


--
-- Name: IDX_6a57f051d82c6d4036c141e107; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_6a57f051d82c6d4036c141e107" ON public.note_unread USING btree ("noteChannelId");


--
-- Name: IDX_6d8084ec9496e7334a4602707e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_6d8084ec9496e7334a4602707e" ON public.channel_following USING btree ("followerId");


--
-- Name: IDX_6fc0ec357d55a18646262fdfff; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_6fc0ec357d55a18646262fdfff" ON public.clip_note USING btree ("noteId", "clipId");


--
-- Name: IDX_702ad5ae993a672e4fbffbcd38; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_702ad5ae993a672e4fbffbcd38" ON public.note_edit USING btree ("noteId");


--
-- Name: IDX_70ab9786313d78e4201d81cdb8; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_70ab9786313d78e4201d81cdb8" ON public.muted_note USING btree ("noteId");


--
-- Name: IDX_70ba8f6af34bc924fc9e12adb8; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_70ba8f6af34bc924fc9e12adb8" ON public.access_token USING btree (token);


--
-- Name: IDX_7125a826ab192eb27e11d358a5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_7125a826ab192eb27e11d358a5" ON public.note USING btree ("userHost");


--
-- Name: IDX_71cb7b435b7c0d4843317e7e16; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_71cb7b435b7c0d4843317e7e16" ON public.channel USING btree ("createdAt");


--
-- Name: IDX_78787741f9010886796f2320a4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_78787741f9010886796f2320a4" ON public.user_group_invite USING btree ("userId", "userGroupId");


--
-- Name: IDX_796a8c03959361f97dc2be1d5c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_796a8c03959361f97dc2be1d5c" ON public.note USING btree ("visibleUserIds");


--
-- Name: IDX_7f7f1c66f48e9a8e18a33bc515; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_7f7f1c66f48e9a8e18a33bc515" ON public.user_ip USING btree ("userId");


--
-- Name: IDX_7fa20a12319c7f6dc3aed98c0a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_7fa20a12319c7f6dc3aed98c0a" ON public.poll USING btree ("userHost");


--
-- Name: IDX_8063a0586ed1dfbe86e982d961; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8063a0586ed1dfbe86e982d961" ON public.webhook USING btree ("on");


--
-- Name: IDX_80ca6e6ef65fb9ef34ea8c90f4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_80ca6e6ef65fb9ef34ea8c90f4" ON public."user" USING btree ("updatedAt");


--
-- Name: IDX_8125f950afd3093acb10d2db8a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8125f950afd3093acb10d2db8a" ON public.channel_note_pining USING btree ("channelId");


--
-- Name: IDX_823bae55bd81b3be6e05cff438; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_823bae55bd81b3be6e05cff438" ON public.channel USING btree ("userId");


--
-- Name: IDX_8288151386172b8109f7239ab2; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8288151386172b8109f7239ab2" ON public.announcement_read USING btree ("userId");


--
-- Name: IDX_83f0862e9bae44af52ced7099e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_83f0862e9bae44af52ced7099e" ON public.promo_note USING btree ("userId");


--
-- Name: IDX_845254b3eaf708ae8a6cac3026; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_845254b3eaf708ae8a6cac3026" ON public.__chart__users USING btree (date);


--
-- Name: IDX_860fa6f6c7df5bb887249fba22; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_860fa6f6c7df5bb887249fba22" ON public.drive_file USING btree ("userId");


--
-- Name: IDX_88937d94d7443d9a99a76fa5c0; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_88937d94d7443d9a99a76fa5c0" ON public.note USING btree (tags);


--
-- Name: IDX_89a29c9237b8c3b6b3cbb4cb30; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_89a29c9237b8c3b6b3cbb4cb30" ON public.note_unread USING btree ("isSpecified");


--
-- Name: IDX_8bdcd3dd2bddb78014999a16ce; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8bdcd3dd2bddb78014999a16ce" ON public.drive_file USING btree ("maybePorn");


--
-- Name: IDX_8bfa548c2b31f9e07db113773e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_8bfa548c2b31f9e07db113773e" ON public.__chart_day__network USING btree (date);


--
-- Name: IDX_8d5afc98982185799b160e10eb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_8d5afc98982185799b160e10eb" ON public.instance USING btree (host);


--
-- Name: IDX_8f1a239bd077c8864a20c62c2c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8f1a239bd077c8864a20c62c2c" ON public.gallery_post USING btree ("createdAt");


--
-- Name: IDX_8f589cf056ff51f09d6096f645; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_8f589cf056ff51f09d6096f645" ON public.__chart_day__hashtag USING btree (date, "group");


--
-- Name: IDX_8fd5215095473061855ceb948c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_8fd5215095473061855ceb948c" ON public.gallery_like USING btree ("userId");


--
-- Name: IDX_90148bbc2bf0854428786bfc15; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_90148bbc2bf0854428786bfc15" ON public.page USING btree ("visibleUserIds");


--
-- Name: IDX_90f7da835e4c10aca6853621e1; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_90f7da835e4c10aca6853621e1" ON public.user_list_joining USING btree ("userId", "userListId");


--
-- Name: IDX_924fa71815cfa3941d003702a0; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_924fa71815cfa3941d003702a0" ON public.announcement_read USING btree ("userId", "announcementId");


--
-- Name: IDX_92779627994ac79277f070c91e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_92779627994ac79277f070c91e" ON public.drive_file USING btree ("userHost");


--
-- Name: IDX_93060675b4a79a577f31d260c6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_93060675b4a79a577f31d260c6" ON public.muting USING btree ("muterId");


--
-- Name: IDX_9657d55550c3d37bfafaf7d4b0; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_9657d55550c3d37bfafaf7d4b0" ON public.promo_read USING btree ("userId");


--
-- Name: IDX_97754ca6f2baff9b4abb7f853d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_97754ca6f2baff9b4abb7f853d" ON public.sw_subscription USING btree ("userId");


--
-- Name: IDX_985b836dddd8615e432d7043dd; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_985b836dddd8615e432d7043dd" ON public.gallery_post USING btree ("userId");


--
-- Name: IDX_98a1bc5cb30dfd159de056549f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_98a1bc5cb30dfd159de056549f" ON public.blocking USING btree ("blockerId", "blockeeId");


--
-- Name: IDX_9949557d0e1b2c19e5344c171e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_9949557d0e1b2c19e5344c171e" ON public.access_token USING btree ("userId");


--
-- Name: IDX_NOTE_MENTIONS; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_NOTE_MENTIONS" ON public.note USING gin (mentions);


--
-- Name: IDX_NOTE_TAGS; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_NOTE_TAGS" ON public.note USING gin (tags);


--
-- Name: IDX_NOTE_VISIBLE_USER_IDS; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_NOTE_VISIBLE_USER_IDS" ON public.note USING gin ("visibleUserIds");


--
-- Name: IDX_a012eaf5c87c65da1deb5fdbfa; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a012eaf5c87c65da1deb5fdbfa" ON public.clip_note USING btree ("noteId");


--
-- Name: IDX_a08ad074601d204e0f69da9a95; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a08ad074601d204e0f69da9a95" ON public.moderation_log USING btree ("userId");


--
-- Name: IDX_a0cd75442dd10d0643a17c4a49; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a0cd75442dd10d0643a17c4a49" ON public.__chart__test_unique USING btree (date, "group");


--
-- Name: IDX_a1efd3e0048a5f2793a47360dc; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a1efd3e0048a5f2793a47360dc" ON public.__chart__network USING btree (date);


--
-- Name: IDX_a27b942a0d6dcff90e3ee9b5e8; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a27b942a0d6dcff90e3ee9b5e8" ON public."user" USING btree ("usernameLower");


--
-- Name: IDX_a319e5dbf47e8a17497623beae; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a319e5dbf47e8a17497623beae" ON public.__chart__test USING btree (date, "group");


--
-- Name: IDX_a40b8df8c989d7db937ea27cf6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a40b8df8c989d7db937ea27cf6" ON public.drive_file USING btree (type);


--
-- Name: IDX_a42c93c69989ce1d09959df4cf; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a42c93c69989ce1d09959df4cf" ON public.note_watching USING btree ("userId", "noteId");


--
-- Name: IDX_a7eba67f8b3fa27271e85d2e26; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a7eba67f8b3fa27271e85d2e26" ON public.drive_file USING btree ("isSensitive");


--
-- Name: IDX_a7fd92dd6dc519e6fb435dd108; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_a7fd92dd6dc519e6fb435dd108" ON public.follow_request USING btree ("followerId");


--
-- Name: IDX_a848f66d6cec11980a5dd59582; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a848f66d6cec11980a5dd59582" ON public.__chart_day__ap_request USING btree (date);


--
-- Name: IDX_a854e557b1b14814750c7c7b0c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a854e557b1b14814750c7c7b0c" ON public."user" USING btree (token);


--
-- Name: IDX_a8c6bfd637d3f1d67a27c48e27; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_a8c6bfd637d3f1d67a27c48e27" ON public.muted_note USING btree ("noteId", "userId");


--
-- Name: IDX_ad0c221b25672daf2df320a817; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_ad0c221b25672daf2df320a817" ON public.note_reaction USING btree ("userId", "noteId");


--
-- Name: IDX_ae1d917992dd0c9d9bbdad06c4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_ae1d917992dd0c9d9bbdad06c4" ON public.page USING btree ("userId");


--
-- Name: IDX_ae7aab18a2641d3e5f25e0c4ea; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_ae7aab18a2641d3e5f25e0c4ea" ON public.note_thread_muting USING btree ("userId", "threadId");


--
-- Name: IDX_aecfbd5ef60374918e63ee95fa; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_aecfbd5ef60374918e63ee95fa" ON public.poll_vote USING btree ("noteId");


--
-- Name: IDX_af639b066dfbca78b01a920f8a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_af639b066dfbca78b01a920f8a" ON public.page USING btree ("updatedAt");


--
-- Name: IDX_b0134ec406e8d09a540f818288; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b0134ec406e8d09a540f818288" ON public.note_watching USING btree ("userId");


--
-- Name: IDX_b11a5e627c41d4dc3170f1d370; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b11a5e627c41d4dc3170f1d370" ON public.notification USING btree ("createdAt");


--
-- Name: IDX_b14489029e4b3aaf4bba5fb524; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_b14489029e4b3aaf4bba5fb524" ON public.__chart__test_grouped USING btree (date, "group");


--
-- Name: IDX_b37dafc86e9af007e3295c2781; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b37dafc86e9af007e3295c2781" ON public.emoji USING btree (name);


--
-- Name: IDX_b77d4dd9562c3a899d9a286fcd; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_b77d4dd9562c3a899d9a286fcd" ON public.__chart__per_user_following USING btree (date, "group");


--
-- Name: IDX_b7fcefbdd1c18dce86687531f9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b7fcefbdd1c18dce86687531f9" ON public.user_list USING btree ("userId");


--
-- Name: IDX_b82c19c08afb292de4600d99e4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b82c19c08afb292de4600d99e4" ON public.page USING btree (name);


--
-- Name: IDX_b9a354f7941c1e779f3b33aea6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_b9a354f7941c1e779f3b33aea6" ON public.blocking USING btree ("createdAt");


--
-- Name: IDX_bb90d1956dafc4068c28aa7560; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_bb90d1956dafc4068c28aa7560" ON public.drive_file USING btree ("folderId");


--
-- Name: IDX_be623adaa4c566baf5d29ce0c8; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_be623adaa4c566baf5d29ce0c8" ON public."user" USING btree (uri);


--
-- Name: IDX_bf3a053c07d9fb5d87317c56ee; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_bf3a053c07d9fb5d87317c56ee" ON public.access_token USING btree (session);


--
-- Name: IDX_bfbc6305547539369fe73eb144; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_bfbc6305547539369fe73eb144" ON public.user_group_invitation USING btree ("userId");


--
-- Name: IDX_bfbc6f79ba4007b4ce5097f08d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_bfbc6f79ba4007b4ce5097f08d" ON public.user_note_pining USING btree ("userId");


--
-- Name: IDX_c1fd1c3dfb0627aa36c253fd14; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_c1fd1c3dfb0627aa36c253fd14" ON public.muting USING btree ("expiresAt");


--
-- Name: IDX_c426394644267453e76f036926; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_c426394644267453e76f036926" ON public.note_thread_muting USING btree ("threadId");


--
-- Name: IDX_c5545d4b31cdc684034e33b81c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_c5545d4b31cdc684034e33b81c" ON public.__chart_day__per_user_notes USING btree (date, "group");


--
-- Name: IDX_c55b2b7c284d9fef98026fc88e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_c55b2b7c284d9fef98026fc88e" ON public.drive_file USING btree ("webpublicAccessKey");


--
-- Name: IDX_c8cc87bd0f2f4487d17c651fbf; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_c8cc87bd0f2f4487d17c651fbf" ON public."user" USING btree ("lastActiveDate");


--
-- Name: IDX_c8dfad3b72196dd1d6b5db168a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_c8dfad3b72196dd1d6b5db168a" ON public.drive_file USING btree ("createdAt");


--
-- Name: IDX_cac14a4e3944454a5ce7daa514; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_cac14a4e3944454a5ce7daa514" ON public.messaging_message USING btree ("recipientId");


--
-- Name: IDX_cad6e07c20037f31cdba8a350c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_cad6e07c20037f31cdba8a350c" ON public.__chart_day__users USING btree (date);


--
-- Name: IDX_d4ebdef929896d6dc4a3c5bb48; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_d4ebdef929896d6dc4a3c5bb48" ON public.note USING btree ("threadId");


--
-- Name: IDX_d54a512b822fac7ed52800f6b4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d54a512b822fac7ed52800f6b4" ON public.follow_request USING btree ("followerId", "followeeId");


--
-- Name: IDX_d54b653660d808b118e36c184c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d54b653660d808b118e36c184c" ON public.__chart_day__per_user_reaction USING btree (date, "group");


--
-- Name: IDX_d57f9030cd3af7f63ffb1c267c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_d57f9030cd3af7f63ffb1c267c" ON public.hashtag USING btree ("attachedUsersCount");


--
-- Name: IDX_d5954f3df5e5e3bdfc3c03f390; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d5954f3df5e5e3bdfc3c03f390" ON public.__chart_day__active_users USING btree (date);


--
-- Name: IDX_d5a1b83c7cab66f167e6888188; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_d5a1b83c7cab66f167e6888188" ON public."user" USING btree ("isExplorable");


--
-- Name: IDX_d844bfc6f3f523a05189076efa; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_d844bfc6f3f523a05189076efa" ON public.user_list_joining USING btree ("userId");


--
-- Name: IDX_d85a184c2540d2deba33daf642; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d85a184c2540d2deba33daf642" ON public.drive_file USING btree ("accessKey");


--
-- Name: IDX_d8e07aa18c2d64e86201601aec; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_d8e07aa18c2d64e86201601aec" ON public.muted_note USING btree ("userId");


--
-- Name: IDX_d908433a4953cc13216cd9c274; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d908433a4953cc13216cd9c274" ON public.note_unread USING btree ("userId", "noteId");


--
-- Name: IDX_d9ecaed8c6dc43f3592c229282; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_d9ecaed8c6dc43f3592c229282" ON public.user_group_joining USING btree ("userId", "userGroupId");


--
-- Name: IDX_da522b4008a9f5d7743b87ad55; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_da522b4008a9f5d7743b87ad55" ON public.__chart__test_grouped USING btree (date) WHERE ("group" IS NULL);


--
-- Name: IDX_dab383a36f3c9db4a0c9b02cf3; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_dab383a36f3c9db4a0c9b02cf3" ON public.__chart__test USING btree (date) WHERE ("group" IS NULL);


--
-- Name: IDX_db2098070b2b5a523c58181f74; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_db2098070b2b5a523c58181f74" ON public.abuse_user_report USING btree ("createdAt");


--
-- Name: IDX_dce530b98e454793dac5ec2f5a; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_dce530b98e454793dac5ec2f5a" ON public.user_profile USING btree ("userHost");


--
-- Name: IDX_df1b5f4099e99fb0bc5eae53b6; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_df1b5f4099e99fb0bc5eae53b6" ON public.gallery_like USING btree ("userId", "postId");


--
-- Name: IDX_e10924607d058004304611a436; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e10924607d058004304611a436" ON public.user_group_invite USING btree ("userGroupId");


--
-- Name: IDX_e11e649824a45d8ed01d597fd9; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e11e649824a45d8ed01d597fd9" ON public."user" USING btree ("createdAt");


--
-- Name: IDX_e21cd3646e52ef9c94aaf17c2e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e21cd3646e52ef9c94aaf17c2e" ON public.messaging_message USING btree ("createdAt");


--
-- Name: IDX_e22bf6bda77b6adc1fd9e75c8c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e22bf6bda77b6adc1fd9e75c8c" ON public.notification USING btree ("appAccessTokenId");


--
-- Name: IDX_e4849a3231f38281280ea4c0ee; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_e4849a3231f38281280ea4c0ee" ON public.__chart_day__per_user_following USING btree (date, "group");


--
-- Name: IDX_e56f4beac5746d44bc3e19c80d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_e56f4beac5746d44bc3e19c80d" ON public.__chart__ap_request USING btree (date);


--
-- Name: IDX_e5848eac4940934e23dbc17581; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e5848eac4940934e23dbc17581" ON public.drive_file USING btree (uri);


--
-- Name: IDX_e637cba4dc4410218c4251260e; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e637cba4dc4410218c4251260e" ON public.note_unread USING btree ("noteId");


--
-- Name: IDX_e74022ce9a074b3866f70e0d27; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_e74022ce9a074b3866f70e0d27" ON public.drive_file USING btree ("thumbnailAccessKey");


--
-- Name: IDX_e7c0567f5261063592f022e9b5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_e7c0567f5261063592f022e9b5" ON public.note USING btree ("createdAt");


--
-- Name: IDX_e9793f65f504e5a31fbaedbf2f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_e9793f65f504e5a31fbaedbf2f" ON public.user_group_invitation USING btree ("userId", "userGroupId");


--
-- Name: IDX_ebe99317bbbe9968a0c6f579ad; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_ebe99317bbbe9968a0c6f579ad" ON public.clip_note USING btree ("clipId");


--
-- Name: IDX_ec96b4fed9dae517e0dbbe0675; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_ec96b4fed9dae517e0dbbe0675" ON public.muting USING btree ("muteeId");


--
-- Name: IDX_f1a461a618fa1755692d0e0d59; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f1a461a618fa1755692d0e0d59" ON public.attestation_challenge USING btree ("userId");


--
-- Name: IDX_f22169eb10657bded6d875ac8f; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f22169eb10657bded6d875ac8f" ON public.note USING btree ("channelId");


--
-- Name: IDX_f272c8c8805969e6a6449c77b3; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f272c8c8805969e6a6449c77b3" ON public.webhook USING btree ("userId");


--
-- Name: IDX_f2d744d9a14d0dfb8b96cb7fc5; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f2d744d9a14d0dfb8b96cb7fc5" ON public.gallery_post USING btree ("isSensitive");


--
-- Name: IDX_f36fed37d6d4cdcc68c803cd9c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_f36fed37d6d4cdcc68c803cd9c" ON public.channel_note_pining USING btree ("channelId", "noteId");


--
-- Name: IDX_f3a1b4bd0c7cabba958a0c0b23; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f3a1b4bd0c7cabba958a0c0b23" ON public.user_group_joining USING btree ("userId");


--
-- Name: IDX_f49922d511d666848f250663c4; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f49922d511d666848f250663c4" ON public.app USING btree (secret);


--
-- Name: IDX_f4fc06e49c0171c85f1c48060d; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f4fc06e49c0171c85f1c48060d" ON public.drive_folder USING btree ("userId");


--
-- Name: IDX_f631d37835adb04792e361807c; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f631d37835adb04792e361807c" ON public.gallery_post USING btree ("updatedAt");


--
-- Name: IDX_f86d57fbca33c7a4e6897490cc; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f86d57fbca33c7a4e6897490cc" ON public.muting USING btree ("createdAt");


--
-- Name: IDX_f8d8b93740ad12c4ce8213a199; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_f8d8b93740ad12c4ce8213a199" ON public.abuse_user_report USING btree ("reporterHost");


--
-- Name: IDX_fa99d777623947a5b05f394cae; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_fa99d777623947a5b05f394cae" ON public."user" USING btree (tags);


--
-- Name: IDX_fb9d21ba0abb83223263df6bcb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_fb9d21ba0abb83223263df6bcb" ON public.registry_item USING btree ("userId");


--
-- Name: IDX_fbb4297c927a9b85e9cefa2eb1; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_fbb4297c927a9b85e9cefa2eb1" ON public.page USING btree ("createdAt");


--
-- Name: IDX_fcdafee716dfe9c3b5fde90f30; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_fcdafee716dfe9c3b5fde90f30" ON public.following USING btree ("followeeHost");


--
-- Name: IDX_fea7c0278325a1a2492f2d6acb; Type: INDEX; Schema: public; Owner: calckey
--

CREATE UNIQUE INDEX "IDX_fea7c0278325a1a2492f2d6acb" ON public.__chart_day__instance USING btree (date, "group");


--
-- Name: IDX_ff9ca3b5f3ee3d0681367a9b44; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_ff9ca3b5f3ee3d0681367a9b44" ON public.user_security_key USING btree ("userId");


--
-- Name: IDX_note_url; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_note_url" ON public.note USING btree (url);


--
-- Name: IDX_renote_muting_createdAt; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_renote_muting_createdAt" ON public.muting USING btree ("createdAt");


--
-- Name: IDX_renote_muting_muteeId; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_renote_muting_muteeId" ON public.muting USING btree ("muteeId");


--
-- Name: IDX_renote_muting_muterId; Type: INDEX; Schema: public; Owner: calckey
--

CREATE INDEX "IDX_renote_muting_muterId" ON public.muting USING btree ("muterId");


--
-- Name: drive_folder FK_00ceffb0cdc238b3233294f08f2; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_folder
    ADD CONSTRAINT "FK_00ceffb0cdc238b3233294f08f2" FOREIGN KEY ("parentId") REFERENCES public.drive_folder(id) ON DELETE SET NULL;


--
-- Name: note_watching FK_03e7028ab8388a3f5e3ce2a8619; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_watching
    ADD CONSTRAINT "FK_03e7028ab8388a3f5e3ce2a8619" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: abuse_user_report FK_04cc96756f89d0b7f9473e8cdf3; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.abuse_user_report
    ADD CONSTRAINT "FK_04cc96756f89d0b7f9473e8cdf3" FOREIGN KEY ("reporterId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: blocking FK_0627125f1a8a42c9a1929edb552; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.blocking
    ADD CONSTRAINT "FK_0627125f1a8a42c9a1929edb552" FOREIGN KEY ("blockerId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: abuse_user_report FK_08b883dd5fdd6f9c4c1572b36de; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.abuse_user_report
    ADD CONSTRAINT "FK_08b883dd5fdd6f9c4c1572b36de" FOREIGN KEY ("assigneeId") REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: note_favorite FK_0e00498f180193423c992bc4370; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_favorite
    ADD CONSTRAINT "FK_0e00498f180193423c992bc4370" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: channel_following FK_0e43068c3f92cab197c3d3cd86e; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_following
    ADD CONSTRAINT "FK_0e43068c3f92cab197c3d3cd86e" FOREIGN KEY ("followeeId") REFERENCES public.channel(id) ON DELETE CASCADE;


--
-- Name: page_like FK_0e61efab7f88dbb79c9166dbb48; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page_like
    ADD CONSTRAINT "FK_0e61efab7f88dbb79c9166dbb48" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_group_invite FK_1039988afa3bf991185b277fe03; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invite
    ADD CONSTRAINT "FK_1039988afa3bf991185b277fe03" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: channel_note_pining FK_10b19ef67d297ea9de325cd4502; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_note_pining
    ADD CONSTRAINT "FK_10b19ef67d297ea9de325cd4502" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: user_publickey FK_10c146e4b39b443ede016f6736d; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_publickey
    ADD CONSTRAINT "FK_10c146e4b39b443ede016f6736d" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: follow_request FK_12c01c0d1a79f77d9f6c15fadd2; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.follow_request
    ADD CONSTRAINT "FK_12c01c0d1a79f77d9f6c15fadd2" FOREIGN KEY ("followeeId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note_reaction FK_13761f64257f40c5636d0ff95ee; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_reaction
    ADD CONSTRAINT "FK_13761f64257f40c5636d0ff95ee" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note FK_17cb3553c700a4985dff5a30ff5; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT "FK_17cb3553c700a4985dff5a30ff5" FOREIGN KEY ("replyId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: following FK_24e0042143a18157b234df186c3; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.following
    ADD CONSTRAINT "FK_24e0042143a18157b234df186c3" FOREIGN KEY ("followeeId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note_thread_muting FK_29c11c7deb06615076f8c95b80a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_thread_muting
    ADD CONSTRAINT "FK_29c11c7deb06615076f8c95b80a" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: clip FK_2b5ec6c574d6802c94c80313fb2; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.clip
    ADD CONSTRAINT "FK_2b5ec6c574d6802c94c80313fb2" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: signin FK_2c308dbdc50d94dc625670055f7; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.signin
    ADD CONSTRAINT "FK_2c308dbdc50d94dc625670055f7" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: messaging_message FK_2c4be03b446884f9e9c502135be; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.messaging_message
    ADD CONSTRAINT "FK_2c4be03b446884f9e9c502135be" FOREIGN KEY ("groupId") REFERENCES public.user_group(id) ON DELETE CASCADE;


--
-- Name: blocking FK_2cd4a2743a99671308f5417759e; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.blocking
    ADD CONSTRAINT "FK_2cd4a2743a99671308f5417759e" FOREIGN KEY ("blockeeId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: notification FK_3b4e96eec8d36a8bbb9d02aa710; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_3b4e96eec8d36a8bbb9d02aa710" FOREIGN KEY ("notifierId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: notification FK_3c601b70a1066d2c8b517094cb9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_3c601b70a1066d2c8b517094cb9" FOREIGN KEY ("notifieeId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_group FK_3d6b372788ab01be58853003c93; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group
    ADD CONSTRAINT "FK_3d6b372788ab01be58853003c93" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: app FK_3f5b0899ef90527a3462d7c2cb3; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.app
    ADD CONSTRAINT "FK_3f5b0899ef90527a3462d7c2cb3" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: note_reaction FK_45145e4953780f3cd5656f0ea6a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_reaction
    ADD CONSTRAINT "FK_45145e4953780f3cd5656f0ea6a" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: note_favorite FK_47f4b1892f5d6ba8efb3057d81a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_favorite
    ADD CONSTRAINT "FK_47f4b1892f5d6ba8efb3057d81a" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: password_reset_request FK_4bb7fd4a34492ae0e6cc8d30ac8; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.password_reset_request
    ADD CONSTRAINT "FK_4bb7fd4a34492ae0e6cc8d30ac8" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_profile FK_51cb79b5555effaf7d69ba1cff9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT "FK_51cb79b5555effaf7d69ba1cff9" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note FK_52ccc804d7c69037d558bac4c96; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT "FK_52ccc804d7c69037d558bac4c96" FOREIGN KEY ("renoteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: messaging_message FK_535def119223ac05ad3fa9ef64b; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.messaging_message
    ADD CONSTRAINT "FK_535def119223ac05ad3fa9ef64b" FOREIGN KEY ("fileId") REFERENCES public.drive_file(id) ON DELETE CASCADE;


--
-- Name: messaging_message FK_5377c307783fce2b6d352e1203b; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.messaging_message
    ADD CONSTRAINT "FK_5377c307783fce2b6d352e1203b" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note_unread FK_56b0166d34ddae49d8ef7610bb9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_unread
    ADD CONSTRAINT "FK_56b0166d34ddae49d8ef7610bb9" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user FK_58f5c71eaab331645112cf8cfa5; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "FK_58f5c71eaab331645112cf8cfa5" FOREIGN KEY ("avatarId") REFERENCES public.drive_file(id) ON DELETE SET NULL;


--
-- Name: note FK_5b87d9d19127bd5d92026017a7b; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT "FK_5b87d9d19127bd5d92026017a7b" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_group_invitation FK_5cc8c468090e129857e9fecce5a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invitation
    ADD CONSTRAINT "FK_5cc8c468090e129857e9fecce5a" FOREIGN KEY ("userGroupId") REFERENCES public.user_group(id) ON DELETE CASCADE;


--
-- Name: announcement_read FK_603a7b1e7aa0533c6c88e9bfafe; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.announcement_read
    ADD CONSTRAINT "FK_603a7b1e7aa0533c6c88e9bfafe" FOREIGN KEY ("announcementId") REFERENCES public.announcement(id) ON DELETE CASCADE;


--
-- Name: user_list_joining FK_605472305f26818cc93d1baaa74; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_list_joining
    ADD CONSTRAINT "FK_605472305f26818cc93d1baaa74" FOREIGN KEY ("userListId") REFERENCES public.user_list(id) ON DELETE CASCADE;


--
-- Name: antenna FK_6446c571a0e8d0f05f01c789096; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.antenna
    ADD CONSTRAINT "FK_6446c571a0e8d0f05f01c789096" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: following FK_6516c5a6f3c015b4eed39978be5; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.following
    ADD CONSTRAINT "FK_6516c5a6f3c015b4eed39978be5" FOREIGN KEY ("followerId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: poll_vote FK_66d2bd2ee31d14bcc23069a89f8; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll_vote
    ADD CONSTRAINT "FK_66d2bd2ee31d14bcc23069a89f8" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_group_joining FK_67dc758bc0566985d1b3d399865; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_joining
    ADD CONSTRAINT "FK_67dc758bc0566985d1b3d399865" FOREIGN KEY ("userGroupId") REFERENCES public.user_group(id) ON DELETE CASCADE;


--
-- Name: user_note_pining FK_68881008f7c3588ad7ecae471cf; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_note_pining
    ADD CONSTRAINT "FK_68881008f7c3588ad7ecae471cf" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: channel_following FK_6d8084ec9496e7334a4602707e1; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_following
    ADD CONSTRAINT "FK_6d8084ec9496e7334a4602707e1" FOREIGN KEY ("followerId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_profile FK_6dc44f1ceb65b1e72bacef2ca27; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_profile
    ADD CONSTRAINT "FK_6dc44f1ceb65b1e72bacef2ca27" FOREIGN KEY ("pinnedPageId") REFERENCES public.page(id) ON DELETE SET NULL;


--
-- Name: note_edit FK_702ad5ae993a672e4fbffbcd38c; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_edit
    ADD CONSTRAINT "FK_702ad5ae993a672e4fbffbcd38c" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: antenna FK_709d7d32053d0dd7620f678eeb9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.antenna
    ADD CONSTRAINT "FK_709d7d32053d0dd7620f678eeb9" FOREIGN KEY ("userListId") REFERENCES public.user_list(id) ON DELETE CASCADE;


--
-- Name: muted_note FK_70ab9786313d78e4201d81cdb89; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muted_note
    ADD CONSTRAINT "FK_70ab9786313d78e4201d81cdb89" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: notification FK_769cb6b73a1efe22ddf733ac453; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_769cb6b73a1efe22ddf733ac453" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: channel_note_pining FK_8125f950afd3093acb10d2db8a8; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel_note_pining
    ADD CONSTRAINT "FK_8125f950afd3093acb10d2db8a8" FOREIGN KEY ("channelId") REFERENCES public.channel(id) ON DELETE CASCADE;


--
-- Name: channel FK_823bae55bd81b3be6e05cff4383; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel
    ADD CONSTRAINT "FK_823bae55bd81b3be6e05cff4383" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: announcement_read FK_8288151386172b8109f7239ab28; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.announcement_read
    ADD CONSTRAINT "FK_8288151386172b8109f7239ab28" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: drive_file FK_860fa6f6c7df5bb887249fba22e; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_file
    ADD CONSTRAINT "FK_860fa6f6c7df5bb887249fba22e" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: gallery_like FK_8fd5215095473061855ceb948cf; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.gallery_like
    ADD CONSTRAINT "FK_8fd5215095473061855ceb948cf" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: notification FK_8fe87814e978053a53b1beb7e98; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_8fe87814e978053a53b1beb7e98" FOREIGN KEY ("userGroupInvitationId") REFERENCES public.user_group_invitation(id) ON DELETE CASCADE;


--
-- Name: muting FK_93060675b4a79a577f31d260c67; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muting
    ADD CONSTRAINT "FK_93060675b4a79a577f31d260c67" FOREIGN KEY ("muterId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: promo_read FK_9657d55550c3d37bfafaf7d4b05; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_read
    ADD CONSTRAINT "FK_9657d55550c3d37bfafaf7d4b05" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: sw_subscription FK_97754ca6f2baff9b4abb7f853dd; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.sw_subscription
    ADD CONSTRAINT "FK_97754ca6f2baff9b4abb7f853dd" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: gallery_post FK_985b836dddd8615e432d7043ddb; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.gallery_post
    ADD CONSTRAINT "FK_985b836dddd8615e432d7043ddb" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: access_token FK_9949557d0e1b2c19e5344c171e9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.access_token
    ADD CONSTRAINT "FK_9949557d0e1b2c19e5344c171e9" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: channel FK_999da2bcc7efadbfe0e92d3bc19; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.channel
    ADD CONSTRAINT "FK_999da2bcc7efadbfe0e92d3bc19" FOREIGN KEY ("bannerId") REFERENCES public.drive_file(id) ON DELETE SET NULL;


--
-- Name: clip_note FK_a012eaf5c87c65da1deb5fdbfa3; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.clip_note
    ADD CONSTRAINT "FK_a012eaf5c87c65da1deb5fdbfa3" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: moderation_log FK_a08ad074601d204e0f69da9a954; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.moderation_log
    ADD CONSTRAINT "FK_a08ad074601d204e0f69da9a954" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: access_token FK_a3ff16c90cc87a82a0b5959e560; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.access_token
    ADD CONSTRAINT "FK_a3ff16c90cc87a82a0b5959e560" FOREIGN KEY ("appId") REFERENCES public.app(id) ON DELETE CASCADE;


--
-- Name: promo_read FK_a46a1a603ecee695d7db26da5f4; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_read
    ADD CONSTRAINT "FK_a46a1a603ecee695d7db26da5f4" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: follow_request FK_a7fd92dd6dc519e6fb435dd108f; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.follow_request
    ADD CONSTRAINT "FK_a7fd92dd6dc519e6fb435dd108f" FOREIGN KEY ("followerId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: page FK_a9ca79ad939bf06066b81c9d3aa; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page
    ADD CONSTRAINT "FK_a9ca79ad939bf06066b81c9d3aa" FOREIGN KEY ("eyeCatchingImageId") REFERENCES public.drive_file(id) ON DELETE CASCADE;


--
-- Name: meta FK_ab1bc0c1e209daa77b8e8d212ad; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.meta
    ADD CONSTRAINT "FK_ab1bc0c1e209daa77b8e8d212ad" FOREIGN KEY ("proxyAccountId") REFERENCES public."user"(id) ON DELETE SET NULL;


--
-- Name: page FK_ae1d917992dd0c9d9bbdad06c4a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page
    ADD CONSTRAINT "FK_ae1d917992dd0c9d9bbdad06c4a" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: poll_vote FK_aecfbd5ef60374918e63ee95fa7; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll_vote
    ADD CONSTRAINT "FK_aecfbd5ef60374918e63ee95fa7" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: user FK_afc64b53f8db3707ceb34eb28e2; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT "FK_afc64b53f8db3707ceb34eb28e2" FOREIGN KEY ("bannerId") REFERENCES public.drive_file(id) ON DELETE SET NULL;


--
-- Name: note_watching FK_b0134ec406e8d09a540f8182888; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_watching
    ADD CONSTRAINT "FK_b0134ec406e8d09a540f8182888" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: gallery_like FK_b1cb568bfe569e47b7051699fc8; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.gallery_like
    ADD CONSTRAINT "FK_b1cb568bfe569e47b7051699fc8" FOREIGN KEY ("postId") REFERENCES public.gallery_post(id) ON DELETE CASCADE;


--
-- Name: user_list FK_b7fcefbdd1c18dce86687531f99; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_list
    ADD CONSTRAINT "FK_b7fcefbdd1c18dce86687531f99" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: drive_file FK_bb90d1956dafc4068c28aa7560a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_file
    ADD CONSTRAINT "FK_bb90d1956dafc4068c28aa7560a" FOREIGN KEY ("folderId") REFERENCES public.drive_folder(id) ON DELETE SET NULL;


--
-- Name: notification FK_bd7fab507621e635b32cd31892c; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_bd7fab507621e635b32cd31892c" FOREIGN KEY ("followRequestId") REFERENCES public.follow_request(id) ON DELETE CASCADE;


--
-- Name: user_group_invitation FK_bfbc6305547539369fe73eb144a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invitation
    ADD CONSTRAINT "FK_bfbc6305547539369fe73eb144a" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_note_pining FK_bfbc6f79ba4007b4ce5097f08d6; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_note_pining
    ADD CONSTRAINT "FK_bfbc6f79ba4007b4ce5097f08d6" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: auth_session FK_c072b729d71697f959bde66ade0; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.auth_session
    ADD CONSTRAINT "FK_c072b729d71697f959bde66ade0" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: messaging_message FK_cac14a4e3944454a5ce7daa5142; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.messaging_message
    ADD CONSTRAINT "FK_cac14a4e3944454a5ce7daa5142" FOREIGN KEY ("recipientId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: antenna FK_ccbf5a8c0be4511133dcc50ddeb; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.antenna
    ADD CONSTRAINT "FK_ccbf5a8c0be4511133dcc50ddeb" FOREIGN KEY ("userGroupJoiningId") REFERENCES public.user_group_joining(id) ON DELETE CASCADE;


--
-- Name: page_like FK_cf8782626dced3176038176a847; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.page_like
    ADD CONSTRAINT "FK_cf8782626dced3176038176a847" FOREIGN KEY ("pageId") REFERENCES public.page(id) ON DELETE CASCADE;


--
-- Name: user_list_joining FK_d844bfc6f3f523a05189076efaa; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_list_joining
    ADD CONSTRAINT "FK_d844bfc6f3f523a05189076efaa" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: muted_note FK_d8e07aa18c2d64e86201601aec1; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muted_note
    ADD CONSTRAINT "FK_d8e07aa18c2d64e86201601aec1" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: poll FK_da851e06d0dfe2ef397d8b1bf1b; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.poll
    ADD CONSTRAINT "FK_da851e06d0dfe2ef397d8b1bf1b" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: auth_session FK_dbe037d4bddd17b03a1dc778dee; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.auth_session
    ADD CONSTRAINT "FK_dbe037d4bddd17b03a1dc778dee" FOREIGN KEY ("appId") REFERENCES public.app(id) ON DELETE CASCADE;


--
-- Name: user_group_invite FK_e10924607d058004304611a436a; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_invite
    ADD CONSTRAINT "FK_e10924607d058004304611a436a" FOREIGN KEY ("userGroupId") REFERENCES public.user_group(id) ON DELETE CASCADE;


--
-- Name: notification FK_e22bf6bda77b6adc1fd9e75c8c9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.notification
    ADD CONSTRAINT "FK_e22bf6bda77b6adc1fd9e75c8c9" FOREIGN KEY ("appAccessTokenId") REFERENCES public.access_token(id) ON DELETE CASCADE;


--
-- Name: promo_note FK_e263909ca4fe5d57f8d4230dd5c; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.promo_note
    ADD CONSTRAINT "FK_e263909ca4fe5d57f8d4230dd5c" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: note_unread FK_e637cba4dc4410218c4251260e4; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note_unread
    ADD CONSTRAINT "FK_e637cba4dc4410218c4251260e4" FOREIGN KEY ("noteId") REFERENCES public.note(id) ON DELETE CASCADE;


--
-- Name: clip_note FK_ebe99317bbbe9968a0c6f579adf; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.clip_note
    ADD CONSTRAINT "FK_ebe99317bbbe9968a0c6f579adf" FOREIGN KEY ("clipId") REFERENCES public.clip(id) ON DELETE CASCADE;


--
-- Name: muting FK_ec96b4fed9dae517e0dbbe0675c; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.muting
    ADD CONSTRAINT "FK_ec96b4fed9dae517e0dbbe0675c" FOREIGN KEY ("muteeId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: attestation_challenge FK_f1a461a618fa1755692d0e0d592; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.attestation_challenge
    ADD CONSTRAINT "FK_f1a461a618fa1755692d0e0d592" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: note FK_f22169eb10657bded6d875ac8f9; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.note
    ADD CONSTRAINT "FK_f22169eb10657bded6d875ac8f9" FOREIGN KEY ("channelId") REFERENCES public.channel(id) ON DELETE CASCADE;


--
-- Name: webhook FK_f272c8c8805969e6a6449c77b3c; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.webhook
    ADD CONSTRAINT "FK_f272c8c8805969e6a6449c77b3c" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_group_joining FK_f3a1b4bd0c7cabba958a0c0b231; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_group_joining
    ADD CONSTRAINT "FK_f3a1b4bd0c7cabba958a0c0b231" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_keypair FK_f4853eb41ab722fe05f81cedeb6; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_keypair
    ADD CONSTRAINT "FK_f4853eb41ab722fe05f81cedeb6" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: drive_folder FK_f4fc06e49c0171c85f1c48060d2; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.drive_folder
    ADD CONSTRAINT "FK_f4fc06e49c0171c85f1c48060d2" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: registry_item FK_fb9d21ba0abb83223263df6bcb3; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.registry_item
    ADD CONSTRAINT "FK_fb9d21ba0abb83223263df6bcb3" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: user_security_key FK_ff9ca3b5f3ee3d0681367a9b447; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.user_security_key
    ADD CONSTRAINT "FK_ff9ca3b5f3ee3d0681367a9b447" FOREIGN KEY ("userId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- Name: abuse_user_report fk_7f4e851a35d81b64dda28eee0; Type: FK CONSTRAINT; Schema: public; Owner: calckey
--

ALTER TABLE ONLY public.abuse_user_report
    ADD CONSTRAINT fk_7f4e851a35d81b64dda28eee0 FOREIGN KEY ("targetUserId") REFERENCES public."user"(id) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

