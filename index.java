<!DOCTYPE html>
<html lang="en" data-theme="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Placement Readiness Dashboard | Pro Assessment Suite</title>
  
  <!-- Google Fonts -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=JetBrains+Mono:wght@400;500;700&family=Plus+Jakarta+Sans:wght@300;400;500;600;700;800&display=swap" rel="stylesheet">
  
  <!-- FontAwesome Icons CDN -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
  
  <!-- Chart.js CDN -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <!-- Canvas Confetti CDN -->
  <script src="https://cdn.jsdelivr.net/npm/canvas-confetti@1.6.0/dist/confetti.browser.min.js"></script>
  
  <link rel="stylesheet" href="style.css">
</head>
<body>

  <!-- Background Glowing Orbs -->
  <div class="bg-blur blur-1"></div>
  <div class="bg-blur blur-2"></div>
  <div class="bg-blur blur-3"></div>

  <!-- Main Layout -->
  <div class="app-container">
    
    <!-- Top Navigation Header -->
    <header class="main-header">
      <div class="brand">
        <div class="logo-icon">
          <i class="fa-solid fa-graduation-cap"></i>
        </div>
        <div>
          <h1>Placement Readiness <span class="gradient-text">Pro</span></h1>
          <p class="subtitle">AI-Powered Career & Eligibility Assessment Intelligence</p>
        </div>
      </div>
      
      <div class="header-actions">
        <button id="themeToggle" class="btn-icon" title="Toggle Theme">
          <i class="fa-solid fa-moon"></i>
        </button>
        <button id="btnSavedProfiles" class="btn-secondary">
          <i class="fa-solid fa-folder-open"></i> Saved Profiles
        </button>
        <button id="btnExport" class="btn-primary" onclick="exportReport()">
          <i class="fa-solid fa-file-pdf"></i> Export Report
        </button>
      </div>
    </header>

    <!-- Main Grid Dashboard -->
    <main class="dashboard-grid">

      <!-- LEFT COLUMN: Input Controls & Target Configuration -->
      <section class="card input-section">
        <div class="card-header">
          <div class="card-title">
            <i class="fa-solid fa-sliders text-accent"></i>
            <h2>Candidate Profile & Scores</h2>
          </div>
          <span class="badge-pill">Step 1 of 2</span>
        </div>

        <form id="readinessForm" onsubmit="event.preventDefault(); calculateReadiness();">
          
          <!-- Student Info & Role Selection -->
          <div class="form-group">
            <label for="name"><i class="fa-solid fa-user"></i> Student Name</label>
            <input type="text" id="name" placeholder="e.g. Alex Johnson" value="Alex Johnson" required>
          </div>

          <div class="form-row">
            <div class="form-group">
              <label for="targetRole"><i class="fa-solid fa-briefcase"></i> Target Role</label>
              <select id="targetRole" onchange="calculateReadiness()">
                <option value="sde">Software Engineer (SDE)</option>
                <option value="data">Data Analyst / Scientist</option>
                <option value="fullstack">Full-Stack Developer</option>
                <option value="devops">DevOps / Cloud Specialist</option>
                <option value="product">Product & Tech Analyst</option>
              </select>
            </div>

            <div class="form-group">
              <label for="targetTier"><i class="fa-solid fa-building"></i> Target Tier</label>
              <select id="targetTier" onchange="calculateReadiness()">
                <option value="tier1">Tier 1 (MAANG / Top Product)</option>
                <option value="tier2" selected>Tier 2 (High Growth Unicorns)</option>
                <option value="tier3">Tier 3 (IT Services / Consultancies)</option>
              </select>
            </div>
          </div>

          <div class="divider"><span>Core Assessment Metrics (0 - 100)</span></div>

          <!-- 1. Resume Score -->
          <div class="score-input-card">
            <div class="score-input-header">
              <label for="resume">
                <i class="fa-solid fa-file-invoice text-indigo"></i> Resume & Portfolio ATS
              </label>
              <div class="score-value-display">
                <input type="number" id="resume" min="0" max="100" value="82" oninput="syncSlider('resume', 'resumeSlider')">
                <span class="unit">%</span>
              </div>
            </div>
            <input type="range" id="resumeSlider" min="0" max="100" value="82" class="slider" oninput="syncInput('resumeSlider', 'resume')">
            <p class="input-hint"><i class="fa-solid fa-circle-info"></i> ATS compliance, project impact, GitHub & formatting quality.</p>
          </div>

          <!-- 2. Aptitude Score -->
          <div class="score-input-card">
            <div class="score-input-header">
              <label for="aptitude">
                <i class="fa-solid fa-brain text-cyan"></i> Aptitude & Logical Reasoning
              </label>
              <div class="score-value-display">
                <input type="number" id="aptitude" min="0" max="100" value="75" oninput="syncSlider('aptitude', 'aptitudeSlider')">
                <span class="unit">%</span>
              </div>
            </div>
            <input type="range" id="aptitudeSlider" min="0" max="100" value="75" class="slider" oninput="syncInput('aptitudeSlider', 'aptitude')">
            <p class="input-hint"><i class="fa-solid fa-circle-info"></i> Quants, Verbal Ability, DILR & problem-solving speed.</p>
          </div>

          <!-- 3. Coding Score -->
          <div class="score-input-card">
            <div class="score-input-header">
              <label for="coding">
                <i class="fa-solid fa-code text-emerald"></i> Coding & DSA Mastery
              </label>
              <div class="score-value-display">
                <input type="number" id="coding" min="0" max="100" value="70" oninput="syncSlider('coding', 'codingSlider')">
                <span class="unit">%</span>
              </div>
            </div>
            <input type="range" id="codingSlider" min="0" max="100" value="70" class="slider" oninput="syncInput('codingSlider', 'coding')">
            <p class="input-hint"><i class="fa-solid fa-circle-info"></i> Data Structures, Algorithms & LeetCode proficiency.</p>
          </div>

          <!-- 4. Interview Score -->
          <div class="score-input-card">
            <div class="score-input-header">
              <label for="interview">
                <i class="fa-solid fa-comments text-amber"></i> Tech & HR Interview Prep
              </label>
              <div class="score-value-display">
                <input type="number" id="interview" min="0" max="100" value="68" oninput="syncSlider('interview', 'interviewSlider')">
                <span class="unit">%</span>
              </div>
            </div>
            <input type="range" id="interviewSlider" min="0" max="100" value="68" class="slider" oninput="syncInput('interviewSlider', 'interview')">
            <p class="input-hint"><i class="fa-solid fa-circle-info"></i> System Design, CS fundamentals, communication & confidence.</p>
          </div>

          <!-- Action Buttons -->
          <div class="form-actions">
            <button type="submit" class="btn-cta">
              <i class="fa-solid fa-chart-line"></i> Calculate Readiness Index
            </button>
            <button type="button" class="btn-secondary" onclick="saveProfile()">
              <i class="fa-solid fa-bookmark"></i> Save Profile
            </button>
          </div>

        </form>
      </section>

      <!-- RIGHT COLUMN: Output Analytics, Graphs & Recommendations -->
      <section class="analytics-section">
        
        <!-- Score Overview Card -->
        <div class="card overview-card">
          <div class="overview-grid">
            
            <!-- Circular Gauge -->
            <div class="gauge-container">
              <svg class="gauge-svg" viewBox="0 0 160 160">
                <circle class="gauge-bg" cx="80" cy="80" r="70"></circle>
                <circle id="gaugeFill" class="gauge-fill" cx="80" cy="80" r="70" stroke-dasharray="440" stroke-dashoffset="440"></circle>
              </svg>
              <div class="gauge-content">
                <span id="scoreNumber" class="score-number">0</span>
                <span class="score-max">/ 100</span>
                <span class="score-label">Readiness Index</span>
              </div>
            </div>

            <!-- Status & Summary -->
            <div class="status-summary">
              <div class="candidate-meta">
                <h3 id="displayName">Alex Johnson</h3>
                <p id="displayRoleMeta" class="meta-subtitle">Targeting: SDE (Tier 2)</p>
              </div>

              <div id="statusBadge" class="status-badge status-warning">
                <i class="fa-solid fa-circle-notch fa-spin"></i> Calculating...
              </div>

              <p id="resultDescription" class="result-desc">
                Complete your scores on the left panel to generate your personalized placement readiness report and benchmark evaluation.
              </p>

              <div class="mini-stats">
                <div class="stat-item">
                  <span class="stat-label">Weighted Average</span>
                  <span id="statWeighted" class="stat-value">73.8%</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">Strongest Domain</span>
                  <span id="statStrongest" class="stat-value text-emerald">Resume</span>
                </div>
                <div class="stat-item">
                  <span class="stat-label">Priority Focus</span>
                  <span id="statWeakest" class="stat-value text-amber">Interview</span>
                </div>
              </div>
            </div>

          </div>
        </div>

        <!-- Visual Analytics Grid: Radar Chart & Bar Comparison -->
        <div class="charts-row">
          <div class="card chart-card">
            <div class="card-header">
              <div class="card-title">
                <i class="fa-solid fa-chart-pie text-cyan"></i>
                <h3>Competency Breakdown</h3>
              </div>
              <span class="sub-tag">vs. Benchmark</span>
            </div>
            <div class="chart-wrapper">
              <canvas id="radarChart"></canvas>
            </div>
          </div>

          <div class="card chart-card">
            <div class="card-header">
              <div class="card-title">
                <i class="fa-solid fa-chart-simple text-indigo"></i>
                <h3>Domain Performance</h3>
              </div>
              <span class="sub-tag">Cutoff Gap</span>
            </div>
            <div class="chart-wrapper">
              <canvas id="barChart"></canvas>
            </div>
          </div>
        </div>

        <!-- Target Company Eligibility Check -->
        <div class="card company-card">
          <div class="card-header">
            <div class="card-title">
              <i class="fa-solid fa-building-circle-check text-emerald"></i>
              <h3>Company Eligibility & Fit Score</h3>
            </div>
            <span class="sub-tag">Real-time Matching</span>
          </div>

          <div id="companyGrid" class="company-grid"></div>
        </div>

        <!-- Personalized Actionable Roadmap -->
        <div class="card roadmap-card">
          <div class="card-header">
            <div class="card-title">
              <i class="fa-solid fa-list-check text-amber"></i>
              <h3>30-Day Targeted Action Plan</h3>
            </div>
            <span class="sub-tag">Customized Advice</span>
          </div>

          <div id="recommendationsList" class="recommendations-list"></div>
        </div>

      </section>

    </main>

    <!-- Footer -->
    <footer class="main-footer">
      <p>Placement Readiness Dashboard &copy; 2026 | Built for Engineering & Tech Career Growth</p>
    </footer>

  </div>

  <!-- Saved Profiles Modal -->
  <div id="profilesModal" class="modal">
    <div class="modal-content card">
      <div class="modal-header">
        <h3><i class="fa-solid fa-folder-open text-indigo"></i> Saved Student Profiles</h3>
        <button class="close-btn" onclick="closeModal()">&times;</button>
      </div>
      <div class="modal-body">
        <ul id="savedProfilesList" class="profiles-list">
          <li class="empty-state">No saved profiles found yet.</li>
        </ul>
      </div>
    </div>
  </div>

  <script src="script.js"></script>
</body>
</html>
