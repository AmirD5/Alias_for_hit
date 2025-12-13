# Alias Game App - Teams Management

A modern Android application built with Kotlin for managing teams in the Alias game. Users can create teams, assign colors, add photos, and manage team information.

---

## 📱 Features

- **Team Management**: Create, edit, and delete teams
- **Color Selection**: Choose from 8 predefined colors for each team
- **Photo Support**: Add custom photos for each team
- **Visual Identification**: Team names and indicators are displayed in their chosen colors
- **Persistent Storage**: All team data is saved locally using Room Database

---

## 🏗️ Project Structure

```
AliasGameApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/hit/aliasgameapp/
│   │   │   │   ├── data/
│   │   │   │   │   ├── dao/
│   │   │   │   │   │   └── TeamDao.kt              # Database access object
│   │   │   │   │   ├── database/
│   │   │   │   │   │   └── AppDatabase.kt          # Room database configuration
│   │   │   │   │   ├── model/
│   │   │   │   │   │   └── Team.kt                 # Team data model
│   │   │   │   │   └── repository/
│   │   │   │   │       └── TeamRepository.kt       # Data repository layer
│   │   │   │   │
│   │   │   │   ├── ui/
│   │   │   │   │   ├── mainlist/
│   │   │   │   │   │   ├── MainListFragment.kt    # Teams list screen
│   │   │   │   │   │   └── TeamAdapter.kt         # RecyclerView adapter
│   │   │   │   │   ├── addedit/
│   │   │   │   │   │   └── AddEditFragment.kt     # Add/Edit team screen
│   │   │   │   │   └── details/
│   │   │   │   │       └── DetailsFragment.kt     # Team details screen
│   │   │   │   │
│   │   │   │   ├── viewmodel/
│   │   │   │   │   └── TeamViewModel.kt           # ViewModel for team operations
│   │   │   │   │
│   │   │   │   └── MainActivity.kt                # Main activity (Navigation host)
│   │   │   │
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml          # Main activity layout
│   │   │   │   │   ├── fragment_main_list.xml     # Teams list layout
│   │   │   │   │   ├── fragment_add_edit.xml      # Add/Edit team layout
│   │   │   │   │   ├── fragment_details.xml       # Team details layout
│   │   │   │   │   └── item_team.xml              # Team list item layout
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml                # String resources
│   │   │   │   │   ├── colors.xml                 # Color definitions
│   │   │   │   │   └── arrays.xml                 # Color picker options
│   │   │   │   ├── navigation/
│   │   │   │   │   └── nav_graph.xml              # Navigation graph
│   │   │   │   └── drawable/
│   │   │   │
│   │   │   └── AndroidManifest.xml
│   │   │
│   │   ├── androidTest/                           # Instrumented tests
│   │   └── test/                                  # Unit tests
│   │
│   └── build.gradle.kts                          # App-level Gradle config
│
├── gradle/
│   └── libs.versions.toml                        # Dependency version catalog
├── build.gradle.kts                              # Project-level Gradle config
└── settings.gradle.kts                           # Project settings
```

---

## 💾 Data Storage

### 1. **Database Storage (Room Database)**

**Location**: Internal app storage (SQLite database)
- **Path**: `/data/data/com.hit.aliasgameapp/databases/`
- **Database Name**: `team_database`
- **Table Name**: `teams`

**Team Table Schema**:
```kotlin
@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,              // Auto-generated unique ID
    val name: String,             // Team name (required)
    val color: String,            // Color name (e.g., "Red", "Blue")
    val notes: String = "",       // Optional notes
    val imagePath: String? = null // Path to team photo
)
```

**Database Access**:
- **DAO**: `TeamDao.kt` - Defines database operations (CRUD)
- **Database**: `AppDatabase.kt` - Room database configuration
- **Repository**: `TeamRepository.kt` - Abstracts data access
- **ViewModel**: `TeamViewModel.kt` - Manages UI-related data

### 2. **Image Storage**

**Location**: Internal app storage (private files directory)
- **Path**: `/data/data/com.hit.aliasgameapp/files/`
- **File Naming**: `team_photo_<timestamp>.jpg`
- **Example**: `team_photo_1702456789123.jpg`

**How Images Are Stored**:
1. User selects an image from gallery
2. Image is copied to app's internal storage
3. File path is saved in database as `imagePath`
4. Images are loaded using URI when displaying teams

### 3. **Data Persistence**

- **Room Database**: Provides automatic data persistence
- **LiveData**: Ensures UI updates automatically when data changes
- **Coroutines**: All database operations run asynchronously
- **No Cloud**: All data is stored locally on the device

---

## 🏛️ Architecture

### **MVVM (Model-View-ViewModel) Pattern**

```
┌─────────────────────────────────────────────────┐
│                    View Layer                    │
│  (Fragments: MainList, AddEdit, Details)        │
└────────────────┬────────────────────────────────┘
                 │ observes LiveData
                 │ calls methods
┌────────────────▼────────────────────────────────┐
│               ViewModel Layer                    │
│            (TeamViewModel)                       │
└────────────────┬────────────────────────────────┘
                 │ requests data
                 │ performs operations
┌────────────────▼────────────────────────────────┐
│             Repository Layer                     │
│           (TeamRepository)                       │
└────────────────┬────────────────────────────────┘
                 │ uses DAO
┌────────────────▼────────────────────────────────┐
│               Data Layer                         │
│  (Room Database + DAO + Entity)                 │
└─────────────────────────────────────────────────┘
```

### **Key Components**:

1. **Model** (`Team.kt`): Data structure representing a team
2. **View** (Fragments + Layouts): UI components that display data
3. **ViewModel** (`TeamViewModel.kt`): Manages UI data and business logic
4. **Repository** (`TeamRepository.kt`): Single source of truth for data
5. **DAO** (`TeamDao.kt`): Database operations interface
6. **Database** (`AppDatabase.kt`): Room database instance

---

## 🎨 Color System

### **Available Team Colors**:

| Color Name | Hex Code   | RGB           |
|------------|------------|---------------|
| Red        | `#F44336`  | (244, 67, 54) |
| Blue       | `#2196F3`  | (33, 150, 243)|
| Green      | `#4CAF50`  | (76, 175, 80) |
| Yellow     | `#FFEB3B`  | (255, 235, 59)|
| Orange     | `#FF9800`  | (255, 152, 0) |
| Purple     | `#9C27B0`  | (156, 39, 176)|
| Pink       | `#E91E63`  | (233, 30, 99) |
| Teal       | `#009688`  | (0, 150, 136) |

**Color Application**:
- Team name text is colored with the selected color
- 8dp vertical color bar appears on the left of each team card
- Colors are defined in `res/values/colors.xml`

---

## 📚 Key Technologies

- **Language**: Kotlin
- **UI**: Jetpack Navigation, View Binding, Material Design
- **Database**: Room Persistence Library
- **Asynchronous**: Kotlin Coroutines
- **Architecture**: MVVM with LiveData
- **Image Handling**: Content Provider + Internal Storage

### **Dependencies**:

```kotlin
// Core Android
androidx.core:core-ktx
androidx.appcompat:appcompat
com.google.android.material:material

// Navigation
androidx.navigation:navigation-fragment-ktx
androidx.navigation:navigation-ui-ktx

// Room Database
androidx.room:room-runtime
androidx.room:room-ktx
androidx.room:room-compiler (kapt)

// Lifecycle
androidx.lifecycle:lifecycle-viewmodel-ktx
androidx.lifecycle:lifecycle-livedata-ktx

// Coroutines
org.jetbrains.kotlinx:kotlinx-coroutines-android

// Image Loading
com.github.bumptech.glide:glide
```

---

## 🚀 Getting Started

### **Prerequisites**:
- Android Studio (latest version)
- Android SDK 25+ (minSdk = 25)
- Kotlin 2.0.21

### **Build & Run**:
1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run on emulator or physical device

### **First Launch**:
- App creates database automatically
- No teams exist initially
- Tap "Add a team" button to create first team

---

## 📱 User Flow

1. **Launch App** → Shows welcome screen with teams list
2. **Add Team** → Tap "Add a team" button
3. **Enter Details** → Name, choose color, add photo, add notes
4. **Save** → Team is saved to database and displayed in list
5. **View Team** → Tap on team to see details
6. **Edit Team** → Tap "Edit" button in details screen
7. **Delete Team** → Tap trash icon in team list

---

## 🔐 Data Security

- **Local Storage Only**: No data sent to external servers
- **Private Storage**: Database and images stored in app's private directory
- **No Permissions Required**: Uses internal storage (no WRITE_EXTERNAL_STORAGE needed)
- **App Uninstall**: All data is deleted when app is uninstalled

---

## 📝 Notes for Developers

### **Adding a New Field to Team**:
1. Update `Team.kt` data class
2. Add migration in `AppDatabase.kt`
3. Update `TeamDao.kt` queries if needed
4. Update UI in fragments and layouts

### **Changing Database Schema**:
```kotlin
// In AppDatabase.kt, increment version and add migration
@Database(entities = [Team::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    // Add migration
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Add migration logic
        }
    }
}
```

### **Testing Database Queries**:
- Use Android Studio's Database Inspector
- View live database contents during debugging
- Located in: View → Tool Windows → App Inspection

---

## 📄 License

This project is created for educational purposes as part of the HIT Mobile Development course.

---

## 🤝 Contributing

This is a student project. For questions or suggestions, please contact the project maintainers.

---

**Last Updated**: December 2025
**Version**: 1.0.0
**Target SDK**: 36
**Min SDK**: 25

