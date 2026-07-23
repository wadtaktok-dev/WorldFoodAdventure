
const root = document.documentElement;
const nav = document.getElementById('main-nav');
const menuButton = document.querySelector('.menu-button');
const themeToggle = document.getElementById('theme-toggle');
const savedTheme = localStorage.getItem('wfa-theme');

if (savedTheme) {
  root.dataset.theme = savedTheme;
} else if (window.matchMedia('(prefers-color-scheme: dark)').matches) {
  root.dataset.theme = 'dark';
}

function updateThemeButton() {
  themeToggle.textContent = root.dataset.theme === 'dark' ? '☀' : '☾';
}

updateThemeButton();

themeToggle.addEventListener('click', () => {
  root.dataset.theme = root.dataset.theme === 'dark' ? 'light' : 'dark';
  localStorage.setItem('wfa-theme', root.dataset.theme);
  updateThemeButton();
});

menuButton.addEventListener('click', () => {
  const open = nav.classList.toggle('open');
  menuButton.setAttribute('aria-expanded', String(open));
});

nav.querySelectorAll('a').forEach(link => {
  link.addEventListener('click', () => {
    nav.classList.remove('open');
    menuButton.setAttribute('aria-expanded', 'false');
  });
});

const observer = new IntersectionObserver(entries => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add('visible');
      observer.unobserve(entry.target);
    }
  });
}, { threshold: 0.12 });

document.querySelectorAll('.reveal').forEach(el => observer.observe(el));

const lightbox = document.getElementById('lightbox');
const lightboxImage = document.getElementById('lightbox-image');
const closeButton = document.getElementById('lightbox-close');

document.querySelectorAll('.gallery-item').forEach(item => {
  item.addEventListener('click', () => {
    const image = item.querySelector('img');
    lightboxImage.src = item.dataset.full;
    lightboxImage.alt = image.alt;
    lightbox.showModal();
  });
});

closeButton.addEventListener('click', () => lightbox.close());
lightbox.addEventListener('click', event => {
  if (event.target === lightbox) lightbox.close();
});

document.getElementById('year').textContent = new Date().getFullYear();
