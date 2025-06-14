# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Capacitor plugin (@teamhive/capacitor-single-signon) that provides single sign-on (SSO) functionality for mobile applications. It uses ASWebAuthenticationSession on iOS and Chrome Custom Tabs on Android to handle OAuth/SSO authentication flows.

## Common Commands

### Build and Development
- `npm run build` - Build TypeScript, generate docs, and bundle the plugin
- `npm run verify` - Verify all platforms (iOS, Android, web) build correctly
- `npm run watch` - Watch TypeScript files for changes during development

### Code Quality
- `npm run lint` - Run ESLint, Prettier, and SwiftLint checks
- `npm run fmt` - Auto-fix linting and formatting issues

### Platform-Specific Verification
- `npm run verify:ios` - Build and test iOS implementation
- `npm run verify:android` - Build and test Android implementation
- `npm run verify:web` - Build TypeScript/web implementation

### Documentation
- `npm run docgen` - Generate API documentation from source JSDoc comments

## Architecture

### Plugin Structure
The plugin exposes a single `authenticate()` method that:
1. Accepts a URL and optional custom scheme
2. Opens a secure browser session (ASWebAuthenticationSession on iOS, Chrome Custom Tabs on Android)
3. Handles the OAuth callback and returns the result URL

### Key Files
- `src/definitions.ts` - Plugin interface definition
- `src/index.ts` - Web implementation and plugin registration
- `ios/Plugin/SingleSignOnPlugin.swift` - iOS native implementation
- `android/src/main/java/com/teamhive/capacitor/singlesignon/SingleSignOnPlugin.java` - Android native implementation

### Platform Requirements
- iOS 12+ (uses ASWebAuthenticationSession)
- Android with Chrome Custom Tabs support
- Capacitor 4.6.3+

## Testing Changes

When making changes:
1. Run `npm run verify` to ensure all platforms build
2. For iOS changes, ensure SwiftLint passes with `npm run lint`
3. Update JSDoc comments and regenerate docs with `npm run docgen` if API changes
4. Test the authentication flow on both iOS and Android devices/simulators