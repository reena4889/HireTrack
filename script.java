const ROLE_WEIGHTS = {
  sde: { resume: 0.20, aptitude: 0.20, coding: 0.40, interview: 0.20, name: "Software Engineer (SDE)" },
  data: { resume: 0.20, aptitude: 0.35, coding: 0.25, interview: 0.20, name: "Data Analyst / Scientist" },
  fullstack: { resume: 0.20, aptitude: 0.15, coding: 0.45, interview: 0.20, name: "Full-Stack Developer" },
  devops: { resume: 0.25, aptitude: 0.20, coding: 0.30, interview: 0.25, name: "DevOps / Cloud Specialist" },
  product: { resume: 0.25, aptitude: 0.30, coding: 0.15, interview: 0.30, name: "Product & Tech Analyst" }
};

const TIER_BENCHMARKS = {
  tier1: { minTotal: 82, resume: 85, aptitude: 85, coding: 85, interview: 80, name: "Tier 1 (MAANG / Top Product)" },
  tier2: { minTotal: 70, resume: 75, aptitude: 75, coding: 70, interview: 70, name: "Tier 2 (High Growth Unicorns)" },
  tier3: { minTotal: 50, resume: 60, aptitude: 60, coding: 50, interview: 50, name: "Tier 3 (IT Services / Consultancies)" }
};

const COMPANIES = [
  { name: "Google / Meta", logo: "fa-brands fa-google", tier: "Tier 1", minScore: 84, minCoding: 85 },
  { name: "Microsoft / Amazon", logo: "fa-brands fa-microsoft", tier: "Tier 1", minScore: 80, minCoding: 80 },
  { name: "Flipkart / Razorpay", logo: "fa-solid fa-bolt", tier: "Tier 2", minScore: 75, minCoding: 75 },
  { name: "Atlassian / Uber", logo: "fa-solid fa-code-branch", tier: "Tier 1", minScore: 82, minCoding: 82 },
  { name: "TCS Digital / Ninja", logo: "fa-solid fa-laptop-code", tier: "Tier 3", minScore: 55, minCoding: 50 },
  { name: "Infosys / Wipro", logo: "fa-solid fa-network-wired", tier: "Tier 3", minScore: 50, minCoding: 45 },
  { name: "Accenture / Cognizant", logo: "fa-solid fa-chart-line", tier: "Tier 3", minScore: 52, minCoding: 45 }
];

let radarChartInstance = null;
let barChartInstance = null;

document.addEventListener("DOMContentLoaded", () => {
  setupTheme();
  initCharts();
  calculateReadiness();
  setupEventListeners();
});

function syncSlider(inputId, sliderId) {
  const input = document.getElementById(inputId);
  const slider = document.getElementById(sliderId);
  let val = Math.min(100, Math.max(0, parseInt(input.value) || 0));
  input.value = val;
  slider.value = val;
  calculateReadiness();
}

function syncInput(sliderId, inputId) {
  const slider = document.getElementById(sliderId);
  const input = document.getElementById(inputId);
  input.value = slider.value;
  calculateReadiness();
}

function calculateReadiness() {
  const name = document.getElementById("name").value.trim() || "Candidate";
  const roleKey = document.getElementById("targetRole").value;
  const tierKey = document.getElementById("targetTier").value;

  const resume = parseInt(document.getElementById("resume").value) || 0;
  const aptitude = parseInt(document.getElementById("aptitude").value) || 0;
  const coding = parseInt(document.getElementById("coding").value) || 0;
  const interview = parseInt(document.getElementById("interview").value) || 0;

  const weights = ROLE_WEIGHTS[roleKey];
  const tierInfo = TIER_BENCHMARKS[tierKey];

  const weightedScore = Math.round(
    (resume * weights.resume) +
    (aptitude * weights.aptitude) +
    (coding * weights.coding) +
    (interview * weights.interview)
  );

  document.getElementById("displayName").innerText = name;
  document.getElementById("displayRoleMeta").innerText = `Targeting: ${weights.name} (${tierInfo.name})`;
  document.getElementById("statWeighted").innerText = `${weightedScore}%`;

  const scoresObj = { Resume: resume, Aptitude: aptitude, Coding: coding, Interview: interview };
  const sortedDomains = Object.entries(scoresObj).sort((a, b) => b[1] - a[1]);
  
  document.getElementById("statStrongest").innerText = `${sortedDomains[0][0]} (${sortedDomains[0][1]}%)`;
  document.getElementById("statWeakest").innerText = `${sortedDomains[3][0]} (${sortedDomains[3][1]}%)`;

  updateGauge(weightedScore);
  updateStatusBadge(weightedScore, tierInfo.minTotal, name);
  updateCharts(resume, aptitude, coding, interview, tierInfo);
  updateCompanyGrid(weightedScore, coding);
  generateActionPlan(scoresObj, tierInfo);

  if (weightedScore >= 80 && !window.confettiFired) {
    if (typeof confetti === 'function') {
      confetti({ particleCount: 70, spread: 60, origin: { y: 0.6 } });
    }
    window.confettiFired = true;
  } else if (weightedScore < 80) {
    window.confettiFired = false;
  }
}

function updateGauge(score) {
  const scoreNumberEl = document.getElementById("scoreNumber");
  const gaugeFill = document.getElementById("gaugeFill");

  let start = parseInt(scoreNumberEl.innerText) || 0;
  let duration = 600;
  let startTime = null;

  function animateCount(timestamp) {
    if (!startTime) startTime = timestamp;
    let progress = Math.min((timestamp - startTime) / duration, 1);
    let current = Math.floor(start + progress * (score - start));
    scoreNumberEl.innerText = current;
    if (progress < 1) requestAnimationFrame(animateCount);
  }
  requestAnimationFrame(animateCount);

  const circumference = 440;
  const offset = circumference - (score / 100) * circumference;
  gaugeFill.style.strokeDashoffset = offset;

  if (score >= 75) gaugeFill.style.stroke = "var(--color-coding)";
  else if (score >= 55) gaugeFill.style.stroke = "var(--color-interview)";
  else gaugeFill.style.stroke = "var(--status-danger-border)";
}

function updateStatusBadge(score, requiredMin, name) {
  const badge = document.getElementById("statusBadge");
  const desc = document.getElementById("resultDescription");

  if (score >= requiredMin) {
    badge.className = "status-badge status-ready";
    badge.innerHTML = `<i class="fa-solid fa-circle-check"></i> High Placement Readiness`;
    desc.innerText = `Awesome job ${name}! Your overall skill distribution satisfies the cutoff threshold for your selected target tier. Keep sharpening mock interviews to convert offers!`;
  } else if (score >= requiredMin - 15) {
    badge.className = "status-badge status-warning";
    badge.innerHTML = `<i class="fa-solid fa-triangle-exclamation"></i> Targeted Improvement Needed`;
    desc.innerText = `${name}, you are close to the target benchmark! Work on your priority focus areas below to boost your readiness index above ${requiredMin}%.`;
  } else {
    badge.className = "status-badge status-danger";
    badge.innerHTML = `<i class="fa-solid fa-circle-xmark"></i> Significant Gap - Action Required`;
    desc.innerText = `${name}, your current scores fall short of your selected target tier requirements. Follow the 30-day action plan to systematically build your core skills.`;
  }
}

function initCharts() {
  const isDark = document.documentElement.getAttribute("data-theme") !== "light";
  const gridColor = isDark ? "rgba(255, 255, 255, 0.1)" : "rgba(0, 0, 0, 0.08)";
  const textColor = isDark ? "#9ca3af" : "#475569";

  const ctxRadar = document.getElementById("radarChart").getContext("2d");
  radarChartInstance = new Chart(ctxRadar, {
    type: "radar",
    data: {
      labels: ["Resume ATS", "Aptitude", "Coding DSA", "Interview Prep"],
      datasets: [
        { label: "Your Score", data: [82, 75, 70, 68], backgroundColor: "rgba(99, 102, 241, 0.25)", borderColor: "#6366f1", borderWidth: 2, pointBackgroundColor: "#6366f1" },
        { label: "Target Benchmark", data: [75, 75, 70, 70], backgroundColor: "rgba(56, 189, 248, 0.1)", borderColor: "#38bdf8", borderWidth: 1.5, borderDash: [4, 4], pointBackgroundColor: "#38bdf8" }
      ]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: { r: { min: 0, max: 100, ticks: { display: false }, grid: { color: gridColor }, angleLines: { color: gridColor }, pointLabels: { color: textColor, font: { family: "Plus Jakarta Sans", size: 11, weight: "600" } } } },
      plugins: { legend: { labels: { color: textColor, font: { family: "Plus Jakarta Sans", size: 11 } } } }
    }
  });

  const ctxBar = document.getElementById("barChart").getContext("2d");
  barChartInstance = new Chart(ctxBar, {
    type: "bar",
    data: {
      labels: ["Resume", "Aptitude", "Coding", "Interview"],
      datasets: [{ label: "Score (%)", data: [82, 75, 70, 68], backgroundColor: ["#818cf8", "#38bdf8", "#34d399", "#fbbf24"], borderRadius: 6 }]
    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      scales: { y: { min: 0, max: 100, grid: { color: gridColor }, ticks: { color: textColor } }, x: { grid: { display: false }, ticks: { color: textColor } } },
      plugins: { legend: { display: false } }
    }
  });
}

function updateCharts(resume, aptitude, coding, interview, tierInfo) {
  if (!radarChartInstance || !barChartInstance) return;
  radarChartInstance.data.datasets[0].data = [resume, aptitude, coding, interview];
  radarChartInstance.data.datasets[1].data = [tierInfo.resume, tierInfo.aptitude, tierInfo.coding, tierInfo.interview];
  radarChartInstance.update();

  barChartInstance.data.datasets[0].data = [resume, aptitude, coding, interview];
  barChartInstance.update();
}

function updateCompanyGrid(overallScore, codingScore) {
  const container = document.getElementById("companyGrid");
  container.innerHTML = "";

  COMPANIES.forEach(comp => {
    let isEligible = overallScore >= comp.minScore && codingScore >= comp.minCoding;
    let isClose = (overallScore >= comp.minScore - 8) && !isEligible;
    let pillClass = isEligible ? "pill-eligible" : (isClose ? "pill-partial" : "pill-locked");
    let pillText = isEligible ? "Eligible" : (isClose ? "Near Cutoff" : "Locked");
    let pillIcon = isEligible ? "fa-check" : (isClose ? "fa-triangle-exclamation" : "fa-lock");

    const item = document.createElement("div");
    item.className = "company-card-item";
    item.innerHTML = `
      <div class="company-header">
        <div class="company-name"><i class="${comp.logo} text-indigo"></i><span>${comp.name}</span></div>
        <span class="company-tier-tag">${comp.tier}</span>
      </div>
      <div style="display:flex; justify-between; align-items:center; margin-top:4px;">
        <span class="eligibility-pill ${pillClass}"><i class="fa-solid ${pillIcon}"></i> ${pillText}</span>
        <span style="font-size:0.75rem; color:var(--text-dim);">Min Score: ${comp.minScore}%</span>
      </div>
    `;
    container.appendChild(item);
  });
}

function generateActionPlan(scores, tierInfo) {
  const container = document.getElementById("recommendationsList");
  container.innerHTML = "";
  const adviceList = [];

  if (scores.Coding < tierInfo.coding) {
    adviceList.push({
      icon: "fa-solid fa-code text-emerald",
      title: "Boost Coding & DSA Problem Solving",
      desc: `Your coding score (${scores.Coding}%) is below the ${tierInfo.name} benchmark (${tierInfo.coding}%). Practice 2-3 LeetCode Medium problems daily focusing on Graphs & Dynamic Programming.`
    });
  } else {
    adviceList.push({
      icon: "fa-solid fa-shield-halved text-emerald",
      title: "Coding Mastery Solid",
      desc: "Maintain your coding rhythm by solving weekly contest problems and practicing mock timed contests."
    });
  }

  if (scores.Interview < tierInfo.interview) {
    adviceList.push({
      icon: "fa-solid fa-comments text-amber",
      title: "Sharpen System Design & Behavioral Interviewing",
      desc: `Focus on STAR method for behavioral questions and practice Low-Level/High-Level design concepts (HLD/LLD) to lift interview score from ${scores.Interview}%.`
    });
  }

  if (scores.Resume < tierInfo.resume) {
    adviceList.push({
      icon: "fa-solid fa-file-invoice text-indigo",
      title: "Enhance Resume ATS Score & Project Descriptions",
      desc: "Quantify achievements with metrics (e.g. 'Improved speed by 35%') and ensure key tech stack keywords match target job descriptions."
    });
  }

  if (scores.Aptitude < tierInfo.aptitude) {
    adviceList.push({
      icon: "fa-solid fa-brain text-cyan",
      title: "Speed Up Aptitude & Logical Reasoning",
      desc: "Take timed 30-minute practice tests on quantitative aptitude, data interpretation, and speed math techniques."
    });
  }

  adviceList.forEach(item => {
    const div = document.createElement("div");
    div.className = "rec-item";
    div.innerHTML = `
      <div class="rec-icon" style="background: rgba(255,255,255,0.05);"><i class="${item.icon}"></i></div>
      <div class="rec-content"><h4>${item.title}</h4><p>${item.desc}</p></div>
    `;
    container.appendChild(div);
  });
}

function setupTheme() {
  const toggleBtn = document.getElementById("themeToggle");
  const savedTheme = localStorage.getItem("placement_theme") || "dark";
  document.documentElement.setAttribute("data-theme", savedTheme);
  updateThemeIcon(savedTheme);

  toggleBtn.addEventListener("click", () => {
    const current = document.documentElement.getAttribute("data-theme");
    const next = current === "dark" ? "light" : "dark";
    document.documentElement.setAttribute("data-theme", next);
    localStorage.setItem("placement_theme", next);
    updateThemeIcon(next);
    initCharts();
    calculateReadiness();
  });
}

function updateThemeIcon(theme) {
  const icon = document.querySelector("#themeToggle i");
  icon.className = theme === "dark" ? "fa-solid fa-sun" : "fa-solid fa-moon";
}

function setupEventListeners() {
  document.getElementById("btnSavedProfiles").addEventListener("click", openProfilesModal);
}

function saveProfile() {
  const name = document.getElementById("name").value.trim() || "Candidate";
  const profiles = JSON.parse(localStorage.getItem("saved_placement_profiles") || "[]");

  const profile = {
    id: Date.now(),
    name: name,
    role: document.getElementById("targetRole").value,
    tier: document.getElementById("targetTier").value,
    resume: document.getElementById("resume").value,
    aptitude: document.getElementById("aptitude").value,
    coding: document.getElementById("coding").value,
    interview: document.getElementById("interview").value,
    date: new Date().toLocaleDateString()
  };

  profiles.push(profile);
  localStorage.setItem("saved_placement_profiles", JSON.stringify(profiles));
  alert(`Profile for "${name}" saved successfully!`);
}

function openProfilesModal() {
  const modal = document.getElementById("profilesModal");
  const list = document.getElementById("savedProfilesList");
  const profiles = JSON.parse(localStorage.getItem("saved_placement_profiles") || "[]");

  modal.style.display = "flex";
  if (profiles.length === 0) {
    list.innerHTML = `<li class="empty-state">No saved student profiles found yet.</li>`;
    return;
  }

  list.innerHTML = profiles.map(p => `
    <li class="profile-item">
      <div class="profile-item-info">
        <strong>${p.name}</strong>
        <span>Role: ${p.role.toUpperCase()} | Date: ${p.date}</span>
      </div>
      <button class="btn-secondary" onclick="loadProfile(${p.id})">Load</button>
    </li>
  `).join("");
}

function loadProfile(id) {
  const profiles = JSON.parse(localStorage.getItem("saved_placement_profiles") || "[]");
  const p = profiles.find(item => item.id === id);

  if (p) {
    document.getElementById("name").value = p.name;
    document.getElementById("targetRole").value = p.role;
    document.getElementById("targetTier").value = p.tier;
    document.getElementById("resume").value = p.resume;
    document.getElementById("resumeSlider").value = p.resume;
    document.getElementById("aptitude").value = p.aptitude;
    document.getElementById("aptitudeSlider").value = p.aptitude;
    document.getElementById("coding").value = p.coding;
    document.getElementById("codingSlider").value = p.coding;
    document.getElementById("interview").value = p.interview;
    document.getElementById("interviewSlider").value = p.interview;

    calculateReadiness();
    closeModal();
  }
}

function closeModal() {
  document.getElementById("profilesModal").style.display = "none";
}

function exportReport() {
  window.print();
}
