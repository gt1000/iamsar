# iamsar
search and rescure


## 모듈 설명

### sar-api
`iamsar/sar-api` 디렉토리에는 수색 및 구조 작업을 위한 주요 백엔드 API가 포함되어 있습니다.

- **build.gradle**: Gradle 빌드 파일로, 의존성 및 빌드 구성을 정의합니다.
- **src/**: 소스 코드 디렉토리입니다.

### drift-prediction
`iamsar/drift-prediction` 디렉토리에는 표류 예측 기능을 담당하는 모듈이 포함되어 있습니다.

- **build.gradle**: Gradle 빌드 파일로, 의존성 및 빌드 구성을 정의합니다.
- **src/**: 소스 코드 디렉토리입니다.

### search-planning
`iamsar/search-planning` 디렉토리에는 수색 계획 기능을 담당하는 모듈이 포함되어 있습니다.

- **build.gradle**: Gradle 빌드 파일로, 의존성 및 빌드 구성을 정의합니다.
- **src/**: 소스 코드 디렉토리입니다.

### frontend
`iamsar/frontend` 디렉토리에는 React를 사용한 프론트엔드 애플리케이션이 포함되어 있습니다.

- **build.gradle**: Gradle 빌드 파일로, 프론트엔드 관련 빌드 구성을 정의합니다.
- **src/**: 소스 코드 디렉토리입니다.

## 설정 및 빌드

### Prerequisites
- Java 17
- Gradle 7.3.3 이상
- Node.js 및 npm (frontend 모듈용)

### 빌드 및 실행

1. **프로젝트 클론**

    ```bash
    git clone https://github.com/your-repo/iamsar.git
    cd iamsar
    ```

2. **백엔드 모듈 빌드 및 실행**

    ```bash
    ./gradlew :sar-api:bootRun
    ./gradlew :drift-prediction:build
    ./gradlew :search-planning:build
    ```

3. **프론트엔드 모듈 빌드 및 실행**

    ```bash
    cd frontend/src/main/frontend
    npm install
    npm start
    ```

### 설정 파일

프로젝트의 설정 파일들은 각 모듈의 `build.gradle` 및 `settings.gradle` 파일에 정의되어 있습니다. 필요한 경우 이 파일들을 수정하여 의존성 및 빌드 구성을 조정할 수 있습니다.

---

이 문서는 IAMSAR 프로젝트의 전반적인 구조와 설정 방법을 설명합니다. 각 모듈에 대한 자세한 내용은 해당 모듈의 README 파일을 참고하세요.
