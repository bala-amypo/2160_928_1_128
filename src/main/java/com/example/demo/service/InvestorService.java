Investment Portfolio Rebalancing Alert
This backend is a JWT-secured Spring Boot API that tracks investors' portfo
by asset class and raises rebalancing alerts whenever an asset drifts abov
its configured target allocation. It stores investor profiles, per-asset-class
target rules, and current holdings (with enforced positive values), then
periodically or on demand computes allocation snapshots that sum total
portfolio value and derive percentage weights per asset type (Stocks, Bond
Crypto, Real Estate, Cash, Other). These percentages are compared agains
each investor's active allocation rules; if any asset class breaches its targe
(currentPercentage > targetPercentage), the system records a detailed ale
including severity, message, and asset class, while preserving the snapsho
for historical analysis. All core capabilities—managing investors, configurin
allocation rules, recording holdings, computing snapshots, and listing or
resolving alerts—are exposed through REST endpoints under /api/**,
protected using role-based JWT authentication (INVESTOR, ANALYST, ADMI
and documented via Swagger for easy integration into portfolio dashboard
advisory tools, or robo-advisor workflows.
Constraint: The root package for the project must be com.example.demo.
Project Requirements:Spring Boot + Hibernate + MySQL + JWT + Swagger
o
er
src
ruc ure:
main
java \ corn \ example demo
v config
...i JwtAutnenucauonHitegava
J JwtTokenProv•ider.java
J SecuntyConfig.pva
J SwaggerConfig.java
> controller
dto
J AuthRequest.Java
J
> entity
v exception
J GlobalExceptionHandler.java
J ResourceNotFoundException.java
> repository
security
J Customusertktailsservice.java
> service
util
J AllocationUtils.java
J
DernoApplication.java
STEP O — Technical Constraints Critical for Testing
You must strictly follow these technical rules to pass the automated test suite:
Enums:
Create com.example.demo.entity.enums.AssetClassType with values:
STOCKS, BONDS, CRYPTO, REAL ESTATE, CASH, OTHER.
Create com.example.demo.entity.enums.AlertSeverity with values:
LOW, MEDIUM, HIGH.
Create com.example.demo.entity.enums.RoleType with values:
INVESTOR, ANALYST, ADMIN.
Use these Enums in your Entities instead of Strings.
2.
Dependency Injection:
You must use Constructor Injection for all Service classes. Do not use
Field Injection (@Autowired on fields). The test suite instantiates
services manually using constructors (e.g., new
Allocationsnapshotservicelmpl(repol, rep02, ...)).
Repository Method Signatures (Exact Naming Required):
3.
InvestorProfileRepository: findBylnvestorld (String investorld).
HoldingRecordRepository: findBylnvestorld(Long investorld).
HoldingRecordRepository: findByValueGreaterThan(Double value).
HoldingRecordRepository: findBylnvestorAndAssetClass(Long
investorld, AssetClassType assetClass).
AssetClassAllocationRuleRepository: findBylnvestorld(Long
investorld).
AssetClassAllocationRuleRepository: findActiveRulesHql(Long
investorld) (Must use @Query).
RebalancingAlertRecordRepository: findBylnvestorld (Long investorld)
RebalancingAlertRecordRepository: findByResolvedFalse().
CarbonScoreRecordRepository: findByUserld(Long userld) (Note: This
seems to be a copy-paste error in the test file
testGetScoresByUserUsesRepository, ensure your generic
RebalancingAlert logic handles this or map correctly). Correction: Th
test file references CarbonScore in error, focus on
RebalancingAlertRecordRepository methods above.
4. Service Constructor Signatures: To ensure your code works with the
automated test suite, AllocationSnapshotServicelmpl must accept
dependencies in the following exact order:
(AllocationSnapshotRecordRepository, HoldingRecordRepository,
AssetClassAllocationRuleRepository, RebalancingAlertRecordRepository)
5.
6.
7.
Exception Messages:
When throwing exceptions, your message must contain specific
keywords:
Invalid Percentage: "between O and 100"
Invalid Value: "must be > O".
Invalid Alert Logic: "currentPercentage > targetPercentage"
Entity Not Found: "not found"
JWT Utility:
Create com.example.demo.security_.JwtUtil.
Constructor: public JwtUtil(String secret, long validitylnMs).
Servlet Requirement:
Create com.example.demo.servlet.SimpleStatusServlet extending
HttpServlet.
Map to /status. Return "0K" on GET. (Standard requirement for this
test suite pattern).
STEP 1 - Data Models (Hibernate I JPA)
1. InvestorProfile
Fields: id (Long, PK), investorld (String), fullName (String), email (String,
unique), active (Boolean), createdAt (LocalDateTime)
Rules:
investorld and email must be unique.
active defaults to true.
2. AssetClassAlIocationRule
Fields: id (Long, PK), investorld (Long), assetClass (Enum:
AssetClassType), targetPercentage (Double), active (Boolean)
Rules:
targetPercentage between O and 100.
Active rules used in calculations.
3. HoldingRecord
Fields: id (Long, PK), investorld (Long), assetClass (Enum:
AssetClassType), currentValue (Double), snapshotDate (LocalDateTime)
Rules:
currentValue > O (Throw "must be > O").
4. AllocationSnapshotRecord
Fields: id (Long, PK), investorld (Long), snapshotDate (LocalDateTime),
totalPortfolioValue (Double), allocationJson (String)
Rules:
totalPortfolioValue > O.
5. RebalancingAIertRecord
Fields: id (Long, PK), investorld (Long), assetClass (Enum:
AssetClassType), currentPercentage (Double), targetPercentage
(Double), severity (Enum: Alertseverity), message (String), alertDate
(LocalDateTime), resolved (Boolean)
Rules:
Generated when currentPercentage > targetPercentage (Validation
required).
resolved defaults to false.
6. UserAccount
Fields: id (Long, PK), username (String, unique), email (String, unique),
password (String), role (Enum: RoleType), active (Boolean).
Rules: Username/Email unique. Password hashed.
STEP 2 — Authentication Using JWT
Endpoints:
/auth/register
/auth/login
Access:
All /api/** endpoints require JWT.
Roles: INVESTOR, ANALYST, ADMIN.
STEP 3 - Create Repositories (MySQL)
InvestorProfileRepository
AssetClassAllocationRuleRepository
HoldingRecordRepository
AllocationSnapshotRecordRepository
RebalancingAlertRecordRepository
UserAccountRepository
STEP 4 — Implement Service Interfaces
1. InvestorProfiIeService
createlnvestor(lnvestorProfile investor)
getlnvestorByld(Long id): Throw exception if not found.
findBylnvestorld(String investorld)
getAlllnvestors()
updatelnvestorStatus(Long id, boolean active)
2. AllocationRuleService
createRule(AssetClassAllocationRule rule): Validate percentage 0-101
updateRule(Long id, AssetClassAllocationRule updatedRule)
getRulesBylnvestor(Long investorld)
getActiveRules(Long investorld)
getRuleByld(Long id)
3. HoldingRecordService
recordHolding(HoldingRecord holding): Validate value > O.
getHoldingsBylnvestor(Long investorld)
getHoldingByld(Long id)
getAllHoldings()
4. AllocationSnapshotService
computeSnapshot(Long investorld):
Fetch holdings. Check if empty (Throw "No holdings").
Sum values, calculate percentages.
Compare with active rules.
Save snapshot.
Create alerts if thresholds exceeded.
getSnapshotByld(Long id)
getSnapshotsBylnvestor(Long investorld)
getAllSnapshots()
5. RebalancingAIertService
createAlert(RebalancingAlertRecord alert): Validate logic current > target.
resolveAlert(Long id)
getAlertsBylnvestor(Long investorld)
getAlertByld(Long id)
getAllAlerts()
STEP 5 - REST controllers
All under /api/ with Swagger @Tag annotations.
1. InvestorProfileControlIer (/api/investors)
POST / — Create investor
GET /{id} — Get investor by ID
GET / - List all
PUT /{id}/status — Update status
GET /lookup/ {investorld} — Lookup
2. AllocationRuleController (/api/allocation-rules)
POST / - Create rule
PUT /{id} — Update rule
GET [investor/ {investorld} — Get rules by investor
GET /{id} - Get rule
GET / - List all
3. HoldingRecordControIIer (/api/holdings)
POST / — Record holding
GET [investor/ {investorld} — Get holdings
GET /{id} — Get holding
GET / - List all
4. AllocationSnapshotController (/api/snapshots)
POST [compute/ {investorld} — Compute snapshot
GET [investor/ {investorld} — Get snapshots
GET /{id} — Get snapshot
GET / - List all
5. RebalancingAIertController (/api/alerts)
POST / - Create alert
PUT /{id}/resolve — Resolve alert
GET [investor/ {investorld} — Get alerts
GET /{id} - Get alert
GET / - List all
6. AuthControIler (/auth)
POST [register
POST [login
STEP 6 — Swagger / OpenAPl Requirement
Swagger IJI: [swagger-ui/index.html
Include JWT Bearer authentication.