from pathlib import Path
import argparse
import sys
import subprocess
import shutil

def _exists(*names):
  return lambda p: any((p / n).exists() for n in names)

def _gradle_cmd(p):
  return ["./gradlew" if (p / "gradlew").exists() else "gradle", "clean", "test"]

def _angular_cmd(p):
  return ["npm", "test"]

PROJECT_TYPES = {
  "angular": {
    "detect": _exists("angular.json"),
    "dependencies": [{"file": "package.json", "dependency": "karma-junit-reporter"}],
    "report_path": "test-results",
    "cmd": _angular_cmd
  },
  "spring-gradle": {
    "detect": _exists("build.gradle", "build.gradle.kts"),
    "dependencies": [{"file": "build.gradle", "dependency": "spring-boot-starter-test"}],
    "report_path": "build/test-results/test",
    "cmd": _gradle_cmd
  }
}

def detect(path):
  return [name for name, t in PROJECT_TYPES.items() if t["detect"](path)]

def found_type(path):
  found=detect(path)
  if not found:
    sys.exit(f"Aucun type de projet reconnu dans {path}.")
  if len(found) > 1:
    sys.exit(f"Plusieurs types détectés {found}.")
  return found[0]

def clean_directory(path):
  shutil.rmtree(path, ignore_errors=True)

def check_dependencies(path, type, project):
  missing = []
  for item in project["dependencies"]:
    f = path / item["file"]
    if not f.exists():
      missing.append(f"[{type}] fichier introuvable : {f}")
    elif item["dependency"] not in f.read_text(encoding="utf-8"):
      missing.append(f"[{type}] dépendance {item['dependency']} absente de {item['file']}")
  return missing

def run_tests_and_generate_report(path, type, project):
  cmd = project["cmd"](path)
  print(f"[{type}] {' '.join(cmd)}")
  proc = subprocess.Popen(cmd, cwd=path, text=True,stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
  return proc.wait()

def main():
  parser = argparse.ArgumentParser()
  parser.add_argument("--path", help="dossier du projet", type=str, default=".")
  args = parser.parse_args()

  path = Path(args.path).resolve()
  type = found_type(path)
  project = PROJECT_TYPES[type]
  clean_directory(path / project["report_path"])
  missing = check_dependencies(path, type, project)
  if missing:
    sys.exit("\n".join(missing))
  code = run_tests_and_generate_report(path, type, project)
  match code:
    case 0:
      print("success")
    case _:
      print(f"error: {code}")
  sys.exit(code)

if __name__ == "__main__":
  main()
