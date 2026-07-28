/* ==========================================================================
   Placement Readiness Dashboard - Master Design System
   ========================================================================== */

:root {
  /* Color Palette - Dark Glass Theme */
  --bg-dark: #0b0f19;
  --bg-card: rgba(18, 24, 38, 0.7);
  --bg-card-hover: rgba(26, 35, 56, 0.8);
  --bg-input: rgba(15, 21, 35, 0.9);
  --border-color: rgba(255, 255, 255, 0.08);
  --border-focus: rgba(99, 102, 241, 0.5);

  --text-main: #f3f4f6;
  --text-muted: #9ca3af;
  --text-dim: #6b7280;

  /* Accent Colors */
  --primary: #6366f1;
  --primary-hover: #4f46e5;
  --primary-glow: rgba(99, 102, 241, 0.35);

  --color-resume: #818cf8;
  --color-aptitude: #38bdf8;
  --color-coding: #34d399;
  --color-interview: #fbbf24;

  /* Status Colors */
  --status-ready-bg: rgba(16, 185, 129, 0.15);
  --status-ready-border: #10b981;
  --status-ready-text: #34d399;

  --status-warning-bg: rgba(245, 158, 11, 0.15);
  --status-warning-border: #f59e0b;
  --status-warning-text: #fbbf24;

  --status-danger-bg: rgba(239, 68, 68, 0.15);
  --status-danger-border: #ef4444;
  --status-danger-text: #f87171;

  /* Font Families */
  --font-sans: 'Plus Jakarta Sans', -apple-system, BlinkMacSystemFont, sans-serif;
  --font-mono: 'JetBrains Mono', monospace;

  /* Radii & Shadows */
  --radius-lg: 18px;
  --radius-md: 12px;
  --radius-sm: 8px;

  --shadow-sm: 0 4px 6px -1px rgba(0, 0, 0, 0.3);
  --shadow-glow: 0 10px 30px -5px rgba(99, 102, 241, 0.25);
  --shadow-card: 0 10px 25px -5px rgba(0, 0, 0, 0.5), inset 0 1px 1px rgba(255, 255, 255, 0.05);

  --transition: all 0.25s cubic-bezier(0.4, 0, 0.2, 1);
}

/* Light Theme Variables Override */
[data-theme="light"] {
  --bg-dark: #f8fafc;
  --bg-card: rgba(255, 255, 255, 0.85);
  --bg-card-hover: rgba(241, 245, 249, 0.95);
  --bg-input: #ffffff;
  --border-color: rgba(0, 0, 0, 0.08);
  --border-focus: rgba(99, 102, 241, 0.6);

  --text-main: #0f172a;
  --text-muted: #475569;
  --text-dim: #64748b;

  --shadow-card: 0 10px 25px -5px rgba(0, 0, 0, 0.05), inset 0 1px 1px rgba(255, 255, 255, 0.8);
}

/* Reset & Base Styles */
*, *::before, *::after {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

body {
  font-family: var(--font-sans);
  background-color: var(--bg-dark);
  color: var(--text-main);
  min-height: 100vh;
  line-height: 1.6;
  overflow-x: hidden;
  position: relative;
  transition: background-color 0.3s ease, color 0.3s ease;
}

/* Background Glowing Orbs */
.bg-blur {
  position: fixed;
  border-radius: 50%;
  filter: blur(120px);
  z-index: 0;
  pointer-events: none;
  opacity: 0.45;
}
.blur-1 {
  width: 400px;
  height: 400px;
  background: radial-gradient(circle, #6366f1 0%, rgba(99, 102, 241, 0) 70%);
  top: -100px;
  left: -100px;
}
.blur-2 {
  width: 500px;
  height: 500px;
  background: radial-gradient(circle, #06b6d4 0%, rgba(6, 182, 212, 0) 70%);
  bottom: -150px;
  right: -100px;
}
.blur-3 {
  width: 350px;
  height: 350px;
  background: radial-gradient(circle, #8b5cf6 0%, rgba(139, 92, 246, 0) 70%);
  top: 40%;
  left: 45%;
}

/* App Container */
.app-container {
  max-width: 1380px;
  margin: 0 auto;
  padding: 24px 28px;
  position: relative;
  z-index: 1;
}

/* Header */
.main-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 28px;
  padding-bottom: 20px;
  border-bottom: 1px solid var(--border-color);
  flex-wrap: wrap;
  gap: 16px;
}

.brand {
  display: flex;
  align-items: center;
  gap: 16px;
}

.logo-icon {
  width: 48px;
  height: 48px;
  background: linear-gradient(135deg, var(--primary) 0%, #8b5cf6 100%);
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
  color: #fff;
  box-shadow: var(--shadow-glow);
}

.brand h1 {
  font-size: 1.75rem;
  font-weight: 800;
  letter-spacing: -0.02em;
}

.gradient-text {
  background: linear-gradient(135deg, #818cf8, #38bdf8);
  background-clip: text;
  -webkit-background-clip: text;
  color: transparent;
  -webkit-text-fill-color: transparent;
}

.subtitle {
  font-size: 0.875rem;
  color: var(--text-muted);
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 12px;
}

/* Buttons & Controls */
button {
  font-family: var(--font-sans);
  font-weight: 600;
  cursor: pointer;
  border: none;
  outline: none;
  transition: var(--transition);
}

.btn-primary {
  background: linear-gradient(135deg, var(--primary) 0%, #4f46e5 100%);
  color: #fff;
  padding: 10px 20px;
  border-radius: var(--radius-md);
  box-shadow: var(--shadow-glow);
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-size: 0.9rem;
}
.btn-primary:hover {
  transform: translateY(-2px);
  box-shadow: 0 12px 35px -5px rgba(99, 102, 241, 0.4);
}

.btn-secondary {
  background: var(--bg-card);
  color: var(--text-main);
  border: 1px solid var(--border-color);
  padding: 10px 18px;
  border-radius: var(--radius-md);
  display: inline-flex;
  align-items: center;
  gap: 8px;
  font-size: 0.9rem;
  backdrop-filter: blur(10px);
}
.btn-secondary:hover {
  background: var(--bg-card-hover);
  border-color: var(--text-muted);
}

.btn-icon {
  width: 42px;
  height: 42px;
  border-radius: var(--radius-md);
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  color: var(--text-main);
  font-size: 1.1rem;
  display: flex;
  align-items: center;
  justify-content: center;
  backdrop-filter: blur(10px);
}
.btn-icon:hover {
  background: var(--bg-card-hover);
  color: var(--primary);
}

.btn-cta {
  width: 100%;
  background: linear-gradient(135deg, #6366f1 0%, #8b5cf6 100%);
  color: #fff;
  padding: 14px 20px;
  border-radius: var(--radius-md);
  font-size: 1rem;
  font-weight: 700;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  box-shadow: var(--shadow-glow);
  margin-top: 10px;
}
.btn-cta:hover {
  transform: translateY(-2px);
  filter: brightness(1.1);
}

/* Card Shell */
.card {
  background: var(--bg-card);
  border: 1px solid var(--border-color);
  border-radius: var(--radius-lg);
  padding: 24px;
  box-shadow: var(--shadow-card);
  backdrop-filter: blur(16px);
  -webkit-backdrop-filter: blur(16px);
  transition: var(--transition);
}
.card:hover {
  border-color: rgba(255, 255, 255, 0.14);
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
  padding-bottom: 12px;
  border-bottom: 1px solid var(--border-color);
}

.card-title {
  display: flex;
  align-items: center;
  gap: 10px;
}
.card-title h2, .card-title h3 {
  font-size: 1.15rem;
  font-weight: 700;
}

.badge-pill, .sub-tag {
  background: rgba(99, 102, 241, 0.15);
  color: #818cf8;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.75rem;
  font-weight: 700;
  letter-spacing: 0.05em;
  text-transform: uppercase;
}

/* Dashboard Grid Layout */
.dashboard-grid {
  display: grid;
  grid-template-columns: 440px 1fr;
  gap: 24px;
}

@media (max-width: 1080px) {
  .dashboard-grid {
    grid-template-columns: 1fr;
  }
}

/* Form Controls */
.form-group {
  margin-bottom: 16px;
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.form-group label {
  font-size: 0.85rem;
  font-weight: 600;
  color: var(--text-muted);
  display: flex;
  align-items: center;
  gap: 6px;
}

input[type="text"], select, input[type="number"] {
  width: 100%;
  background: var(--bg-input);
  border: 1px solid var(--border-color);
  border-radius: var(--radius-md);
  padding: 11px 14px;
  color: var(--text-main);
  font-family: var(--font-sans);
  font-size: 0.95rem;
  transition: var(--transition);
}

input[type="text"]:focus, select:focus, input[type="number"]:focus {
  border-color: var(--border-focus);
  outline: none;
  box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.2);
}

.form-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 12px;
}

.divider {
  display: flex;
  align-items: center;
  text-align: center;
  margin: 20px 0 16px;
  color: var(--text-dim);
  font-size: 0.75rem;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  font-weight: 700;
}
.divider::before, .divider::after {
  content: '';
  flex: 1;
  border-bottom: 1px dashed var(--border-color);
}
.divider span {
  padding: 0 10px;
}

/* Score Input Card & Dual Slider */
.score-input-card {
  background: rgba(0, 0, 0, 0.2);
  border: 1px solid var(--border-color);
  border-radius: var(--radius-md);
  padding: 14px 16px;
  margin-bottom: 14px;
  transition: var(--transition);
}
.score-input-card:hover {
  border-color: rgba(255, 255, 255, 0.15);
  background: rgba(0, 0, 0, 0.3);
}

.score-input-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 8px;
}

.score-input-header label {
  font-size: 0.9rem;
  font-weight: 600;
  display: flex;
  align-items: center;
  gap: 8px;
}

.score-value-display {
  display: flex;
  align-items: center;
  gap: 4px;
  width: 80px;
}

.score-value-display input {
  padding: 4px 8px;
  text-align: center;
  font-family: var(--font-mono);
  font-weight: 700;
  font-size: 1rem;
}

.unit {
  font-size: 0.85rem;
  color: var(--text-muted);
  font-weight: 700;
}

/* Range Slider */
.slider {
  -webkit-appearance: none;
  appearance: none;
  width: 100%;
  height: 6px;
  border-radius: 3px;
  background: rgba(255, 255, 255, 0.1);
  outline: none;
  margin: 6px 0 8px;
}

.slider::-webkit-slider-thumb {
  -webkit-appearance: none;
  width: 18px;
  height: 18px;
  border-radius: 50%;
  background: var(--primary);
  cursor: pointer;
  box-shadow: 0 0 10px var(--primary);
  transition: transform 0.15s ease;
}

.slider::-webkit-slider-thumb:hover {
  transform: scale(1.2);
}

.input-hint {
  font-size: 0.72rem;
  color: var(--text-muted);
  margin-top: 2px;
}

.form-actions {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-top: 18px;
}

/* Analytics Section (Right Column) */
.analytics-section {
  display: flex;
  flex-direction: column;
  gap: 24px;
}

/* Overview Card with Gauge */
.overview-grid {
  display: grid;
  grid-template-columns: 180px 1fr;
  gap: 24px;
  align-items: center;
}

@media (max-width: 640px) {
  .overview-grid {
    grid-template-columns: 1fr;
    justify-items: center;
    text-align: center;
  }
}

/* SVG Gauge */
.gauge-container {
  position: relative;
  width: 160px;
  height: 160px;
}

.gauge-svg {
  width: 100%;
  height: 100%;
  transform: rotate(-90deg);
}

.gauge-bg {
  fill: none;
  stroke: rgba(255, 255, 255, 0.06);
  stroke-width: 12;
}

.gauge-fill {
  fill: none;
  stroke: url(#gaugeGradient);
  stroke: var(--primary);
  stroke-width: 12;
  stroke-linecap: round;
  transition: stroke-dashoffset 1.2s cubic-bezier(0.34, 1.56, 0.64, 1), stroke 0.5s ease;
}

.gauge-content {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.score-number {
  font-size: 2.5rem;
  font-weight: 800;
  font-family: var(--font-mono);
  line-height: 1;
}

.score-max {
  font-size: 0.75rem;
  color: var(--text-dim);
  font-weight: 600;
}

.score-label {
  font-size: 0.72rem;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  color: var(--text-muted);
  font-weight: 700;
  margin-top: 2px;
}

/* Status Badges */
.status-summary {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.candidate-meta h3 {
  font-size: 1.4rem;
  font-weight: 800;
}

.meta-subtitle {
  font-size: 0.85rem;
  color: var(--text-muted);
}

.status-badge {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  padding: 8px 16px;
  border-radius: var(--radius-md);
  font-size: 0.95rem;
  font-weight: 700;
  width: fit-content;
  border: 1px solid transparent;
}

.status-ready {
  background: var(--status-ready-bg);
  border-color: var(--status-ready-border);
  color: var(--status-ready-text);
}

.status-warning {
  background: var(--status-warning-bg);
  border-color: var(--status-warning-border);
  color: var(--status-warning-text);
}

.status-danger {
  background: var(--status-danger-bg);
  border-color: var(--status-danger-border);
  color: var(--status-danger-text);
}

.result-desc {
  font-size: 0.9rem;
  color: var(--text-muted);
}

/* Mini Stats Grid */
.mini-stats {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 10px;
  background: rgba(0, 0, 0, 0.2);
  border: 1px solid var(--border-color);
  padding: 12px;
  border-radius: var(--radius-md);
  margin-top: 4px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  gap: 2px;
}

.stat-label {
  font-size: 0.7rem;
  color: var(--text-dim);
  text-transform: uppercase;
  letter-spacing: 0.05em;
  font-weight: 700;
}

.stat-value {
  font-size: 0.95rem;
  font-weight: 700;
  font-family: var(--font-mono);
}

/* Charts Row */
.charts-row {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 24px;
}

@media (max-width: 768px) {
  .charts-row {
    grid-template-columns: 1fr;
  }
}

.chart-wrapper {
  position: relative;
  height: 250px;
  width: 100%;
}

/* Company Eligibility Grid */
.company-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
  gap: 14px;
}

.company-card-item {
  background: rgba(0, 0, 0, 0.2);
  border: 1px solid var(--border-color);
  border-radius: var(--radius-md);
  padding: 14px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  transition: var(--transition);
}
.company-card-item:hover {
  background: rgba(0, 0, 0, 0.35);
  transform: translateY(-2px);
}

.company-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.company-name {
  font-weight: 700;
  font-size: 0.95rem;
  display: flex;
  align-items: center;
  gap: 8px;
}

.company-tier-tag {
  font-size: 0.7rem;
  color: var(--text-dim);
  font-weight: 600;
}

.eligibility-pill {
  font-size: 0.72rem;
  padding: 3px 8px;
  border-radius: 12px;
  font-weight: 700;
  width: fit-content;
}

.pill-eligible {
  background: rgba(16, 185, 129, 0.2);
  color: #34d399;
}
.pill-partial {
  background: rgba(245, 158, 11, 0.2);
  color: #fbbf24;
}
.pill-locked {
  background: rgba(239, 68, 68, 0.2);
  color: #f87171;
}

/* Action Recommendations List */
.recommendations-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.rec-item {
  display: flex;
  align-items: flex-start;
  gap: 14px;
  background: rgba(0, 0, 0, 0.2);
  border: 1px solid var(--border-color);
  padding: 14px;
  border-radius: var(--radius-md);
  transition: var(--transition);
}
.rec-item:hover {
    border-color: rgba(255, 255, 255, 0.15);
}
