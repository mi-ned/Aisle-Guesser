//
//  MainMenuScreen.swift
//  AisleGuessr
//
//  Created by Miroslav Nedeljkovic on 20/09/2025.
//

import SwiftUI

struct MainMenuScreen: View {
    let buttonList = ["disclaimer", "play", "glossary", "settings", "information"]
    
    var body: some View {
        VStack {
            Spacer()
            
            VStack(spacing: 24){
                Image("logo_white")
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .padding(.horizontal, 24)
                
                Text("app_name")
                    .foregroundColor(.white)
                    .font(.largeTitle)
                    .bold()
            }
            
            Spacer()
            
            VStack(spacing: 8) {
                ForEach(buttonList, id: \.self) { title in
                    MainMenuButton(label: title, action: {
                        print("\(title) button tapped!")
                    })
                }
            }
            .padding(.horizontal, 16)
            .padding(.bottom, 16)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color("PrimaryColour"))
        .ignoresSafeArea(.all)
    }
}

#Preview {
    MainMenuScreen()
}
