import 'package:flutter/material.dart';

typedef DirectionCallback = void Function(int dx, int dy);

class VirtualDPad extends StatefulWidget {
  final DirectionCallback onDirectionChange;
  final double size;
  
  const VirtualDPad({
    Key? key,
    required this.onDirectionChange,
    this.size = 100,
  }) : super(key: key);

  @override
  State<VirtualDPad> createState() => _VirtualDPadState();
}

class _VirtualDPadState extends State<VirtualDPad> {
  Offset? _currentTouch;
  int _lastDx = 0;
  int _lastDy = 0;
  
  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onPanStart: _handlePanStart,
      onPanUpdate: _handlePanUpdate,
      onPanEnd: _handlePanEnd,
      child: Container(
        width: widget.size,
        height: widget.size,
        decoration: BoxDecoration(
          shape: BoxShape.circle,
          color: Colors.black54,
          border: Border.all(
            color: Colors.orange,
            width: 2,
          ),
        ),
        child: Stack(
          alignment: Alignment.center,
          children: [
            // Center circle
            Container(
              width: 30,
              height: 30,
              decoration: BoxDecoration(
                shape: BoxShape.circle,
                color: Colors.orange.shade700,
              ),
            ),
            // Direction indicators
            if (_currentTouch != null) ...[
              _DirectionArrow(
                direction: 'up',
                isActive: _lastDy < 0,
              ),
              _DirectionArrow(
                direction: 'down',
                isActive: _lastDy > 0,
              ),
              _DirectionArrow(
                direction: 'left',
                isActive: _lastDx < 0,
              ),
              _DirectionArrow(
                direction: 'right',
                isActive: _lastDx > 0,
              ),
            ],
          ],
        ),
      ),
    );
  }
  
  void _handlePanStart(DragStartDetails details) {
    _handlePanUpdate(DragUpdateDetails(
      globalPosition: details.globalPosition,
      localPosition: details.localPosition,
      delta: Offset.zero,
    ));
  }
  
  void _handlePanUpdate(DragUpdateDetails details) {
    final center = Offset(widget.size / 2, widget.size / 2);
    final localPos = details.localPosition;
    final delta = localPos - center;
    
    setState(() {
      _currentTouch = delta;
    });
    
    // Determine direction based on angle and distance
    if (delta.distance < 10) {
      _updateDirection(0, 0);
    } else {
      final angle = delta.direction;
      int dx = 0;
      int dy = 0;
      
      // Convert angle to direction (8-way)
      if (angle > -3.93 && angle < -2.36) {
        // Up-left
        dx = -1;
        dy = -1;
      } else if (angle >= -2.36 && angle <= -0.785) {
        // Up
        dy = -1;
      } else if (angle > -0.785 && angle < 0.785) {
        // Up-right
        dx = 1;
        dy = -1;
      } else if (angle >= 0.785 && angle <= 2.36) {
        // Right
        dx = 1;
      } else if (angle > 2.36 && angle < 3.93) {
        // Down-right
        dx = 1;
        dy = 1;
      } else if (angle >= 3.93 || angle <= -3.93) {
        // Down
        dy = 1;
      } else if (angle > -3.93 && angle < -2.36) {
        // Down-left
        dx = -1;
        dy = 1;
      } else if (angle >= -2.36 && angle <= -0.785) {
        // Left
        dx = -1;
      }
      
      _updateDirection(dx, dy);
    }
  }
  
  void _handlePanEnd(DragEndDetails details) {
    setState(() {
      _currentTouch = null;
    });
    _updateDirection(0, 0);
  }
  
  void _updateDirection(int dx, int dy) {
    if (_lastDx != dx || _lastDy != dy) {
      _lastDx = dx;
      _lastDy = dy;
      widget.onDirectionChange(dx, dy);
    }
  }
}

class _DirectionArrow extends StatelessWidget {
  final String direction;
  final bool isActive;
  
  const _DirectionArrow({
    required this.direction,
    required this.isActive,
  });

  @override
  Widget build(BuildContext context) {
    final color = isActive ? Colors.green : Colors.orange.shade400;
    
    late Widget child;
    late AlignmentGeometry alignment;
    
    switch (direction) {
      case 'up':
        child = Icon(Icons.arrow_upward, color: color);
        alignment = Alignment.topCenter;
        break;
      case 'down':
        child = Icon(Icons.arrow_downward, color: color);
        alignment = Alignment.bottomCenter;
        break;
      case 'left':
        child = Icon(Icons.arrow_back, color: color);
        alignment = Alignment.centerLeft;
        break;
      case 'right':
        child = Icon(Icons.arrow_forward, color: color);
        alignment = Alignment.centerRight;
        break;
    }
    
    return Positioned(
      top: alignment == Alignment.topCenter ? 8 : null,
      bottom: alignment == Alignment.bottomCenter ? 8 : null,
      left: alignment == Alignment.centerLeft ? 8 : null,
      right: alignment == Alignment.centerRight ? 8 : null,
      child: Opacity(opacity: isActive ? 1.0 : 0.5, child: child),
    );
  }
}
