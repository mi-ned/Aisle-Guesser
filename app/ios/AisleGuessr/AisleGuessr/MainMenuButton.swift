//
//  MainMenuButton.swift
//  AisleGuessr
//
//  Created by Miroslav Nedeljkovic on 20/09/2025.
//

import SwiftUI

struct MainMenuButton: View {
    let label: String
    let action: () -> Void
    
    @State private var isPressed = false
    
    var body: some View {
        Button(action: {
            action()
        }) {
            Text(label)
                .font(.headline)
                .foregroundColor(isPressed ? Color("SecondaryColour") : Color.white)
                .frame(maxWidth: .infinity)
                .padding(.vertical, 12)
                .background(Color.clear)
                .cornerRadius(12)
        }
        .buttonStyle(.plain)
        .simultaneousGesture(
            DragGesture(minimumDistance: 0)
                .onChanged { _ in isPressed = true }
                .onEnded { _ in isPressed = false }
        )
        .animation(.easeInOut, value: isPressed)
    }
}
