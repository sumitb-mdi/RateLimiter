

Extensible :::
	Non Functional Extension :
		-- Distributed Environment.


	Application Of Rate Limiting:
		-- Tenant Based
		-- UserId Based.

	Algorithmic.
		-- Sliding Window Protocol.   (Should be able to switch protol. )

	ConfiguraitonInput Source :
		-- JSON
		-- XML
		-- DB

	Analytics
		-- Usage (tenant base / user based.)

	RealTime Anomaly Detection:
		-- Detect the Malicious user/tenant to allow blocking it.

	Output
		-- Exact time after which call will be allowed.
		-- Type of Limit exceeded (Global Limit / Local Limit)





Functional Requirements :
-- Read Configuration Module.
	Source : JSON

-- READ InMemory Configuration storage.
	V1: inMemory
	Should be extensible to Remote REDIS.

-- Protocol Design:
	V1 : Sliding Implement
	Interfaces for Leaky Bucket, Fixed Window, etc.











Input         [ Module ].      Output


INPUT : (ServiceName, API_Method, Optional<API Name> )

OUTPUT : Allowed / Restricted. ( TYPE : GLOBAL | LOCAL  )



Protocol : Sliding Window.
Key :
	PREFIX + LOCAL + ServiceName + Optional<APIName> + Method
	PREFIX + GLOBAL + ServiceName + Optional<Method>



Value :
	Sorted List of Timestamp

While reading with current time-stamp, keep on deleting the entries older than current time. (Since they will never be used. )



