from pathlib import Path
import argparse
import sys
import subprocess
import shutil

def _gradle_cmd(p):
  return ["./gradlew" if (p / "gradlew").exists() else "gradle", "clean", "test"]

def _angular_cmd(p):
  return ["npm", "test"]

PROJECTS = [
  {
    "name": "olympic-games",
    "dependencies": [{"file": "package.json", "dependency": "karma-junit-reporter"}],
    "report_path": "olympic-games/test-results",
    "cmd": _angular_cmd
  },
  {
    "name": "workshop-organizer",
    "dependencies": [{"file": "build.gradle", "dependency": "spring-boot-starter-test"}],
    "report_path": "workshop-organizer/build/test-results/test",
    "cmd": _gradle_cmd
  }
]

def clean_directory(path):
  shutil.rmtree(path, ignore_errors=True)

def check_dependencies(path, project):
  missing = []
  for item in project["dependencies"]:
    f = path / item["file"]
    if not f.exists():
      missing.append(f"[{project['name']}] fichier introuvable : {f}")
    elif item["dependency"] not in f.read_text(encoding="utf-8"):
      missing.append(f"[{project['name']}] dépendance {item['dependency']} absente de {item['file']}")
  return missing

def run_tests_and_generate_report(path, project):
  cmd = project["cmd"](path)
  print(f"[{project["name"]}] {' '.join(cmd)}")
  proc = subprocess.Popen(cmd, cwd=path, text=True,stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
  return proc.wait()

def main():
  current_path = Path(".").resolve()
  for project in PROJECTS:
    path_project = current_path / project["name"]
    report_path = current_path / project["report_path"]
    clean_directory(report_path)
    missing = check_dependencies(path_project, project)
    if missing:
      print("\n".join(missing))
      continue
    code = run_tests_and_generate_report(path_project, project)
    match code:
      case 0:
        print("success")
      case _:
        print(f"error: {code}")


if __name__ == "__main__":
  main()
